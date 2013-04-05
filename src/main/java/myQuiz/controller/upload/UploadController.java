package myQuiz.controller.upload;

import myQuiz.model.quiz.Answer;
import myQuiz.model.quiz.MultiAnswerQuestion;
import myQuiz.model.quiz.OneAnswerQuestion;
import myQuiz.model.quiz.Question;
import myQuiz.service.QuestionService;
import myQuiz.util.AppLog;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * controls the executions of one submission by the user (the submission)
 */
@Named("upload_ctrl")
@RequestScoped
public class UploadController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -6502974118876986813L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject QuestionService questionService;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

    }

    public void upload() throws IOException, InvalidFormatException {

        NPOIFSFileSystem fs = new NPOIFSFileSystem(new File("/esmmt/questions.xls"));

        try {
            Workbook wb = WorkbookFactory.create(fs);

            Sheet sheet = wb.getSheet("Java");

            int rowStart = Math.min(15, sheet.getFirstRowNum());
            int rowEnd = Math.max(100, sheet.getLastRowNum());

            log.debug("Opening file....");
            log.debug("Row Start : {}", rowStart);
            log.debug("Row End : {}", rowEnd);

            int questionCount = 1;
            int rowNum = rowStart;
            while (rowNum < rowEnd) {

                Cell c = sheet.getRow(rowNum).getCell(0);
                if (c.getStringCellValue().equals("Area")) {

                    if (sheet.getRow(rowNum).getCell(1).getStringCellValue().equals("END"))
                        return;

                    rowNum += 1;
                    Row qr = sheet.getRow(rowNum);
                    String areaStr = qr.getCell(0).getStringCellValue();
                    String levelStr = qr.getCell(1).getStringCellValue();
                    String htmlStr = qr.getCell(2).getStringCellValue();
                    String textStr = qr.getCell(3).getStringCellValue();
                    log.debug("Found question : " + areaStr + ", " + levelStr + ", " + htmlStr + ", " + textStr);

                    rowNum += 1;
                    Cell c1 = sheet.getRow(rowNum).getCell(0);
                    if (c1.getStringCellValue().equals("Correct")) {

                        List<Answer> answers = new ArrayList<Answer>();
                        int countTrueAnswers = 0;
                        rowNum += 1;
                        do {
                            Row ar = sheet.getRow(rowNum);
                            String correctStr = ar.getCell(0).getStringCellValue();
                            String answerStr = ar.getCell(1).getStringCellValue();
                            log.debug("Found answer : " + correctStr + ", " + answerStr);
                            if (correctStr.equals("T"))
                                countTrueAnswers += 1;

                            answers.add(new Answer(answerStr, correctStr.equals("T")));
                            rowNum += 1;
                        }
                        while (!sheet.getRow(rowNum).getCell(0).getStringCellValue().equals("Area"));

                        Question question = (countTrueAnswers > 1) ?
                                new MultiAnswerQuestion() : new OneAnswerQuestion();
                        question.setCode(areaStr.substring(0, 4) + "." + String.format("%04d", questionCount));
                        question.setArea(areaStr);
                        question.setLevel(Question.Level.Assessed);
                        question.setHtmlFormatted(htmlStr.equals("T"));
                        question.setText(textStr);
                        question.addAnswers(answers);

                        questionService.save(question);

                        questionCount += 1;
                    }
                    else {
                        log.error("Format Error, no header 'Correct'");
                        rowNum += 1;
                    }
                }
                else {
                    log.error("Format Error, no header 'Area'");
                    rowNum += 1;
                }
            }
        }
        finally {
            fs.close();
        }

    }
}
