package myQuiz.controller.quiz;

import myQuiz.model.quiz.PossibleAnswer;
import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import org.apache.commons.collections.map.MultiValueMap;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 14/03/13
 * Time: 15.14
 * <p/>
 * prototype of the report for the user, where we list all the questions, the answers of the user
 * and the correct answers if the user was wrong.
 * maybe we can generate this with JSF with some pre-processing of data but the idea was to provide
 * some form of HTML report for the user to download
 * the report have to include inline CSS styles loaded from result_report.css (but non includede as file, we
 * need to inline it so the report is self contained)
 * Still Work in progress, but as a demo it works fine
 * Radio selection are displayed as checkbox.... but no big deal
 */
public class ResultReportGenerator {

    public String generateResultReport(Quiz quiz, MultiValueMap userAnswers) {

        StringBuilder buff = new StringBuilder();

        for (Question q : quiz.getQuestions()) {
            List<PossibleAnswer> list = (List<PossibleAnswer>) userAnswers.get(q);

            generateResultReportForQuestion(buff, q, list);
        }

        return buff.toString();
    }

    private void generateResultReportForQuestion(StringBuilder buff, Question question, List<PossibleAnswer> userAnswers) {

        double score = question.score(userAnswers);
        boolean correctAnswer = !(score < 1.0);

        if (correctAnswer) {

            outputQuestionText(buff, question);

            buff.append("<table>");
            buff.append("<tr>");
            for (PossibleAnswer pa : question.getPossibleAnswers()) {
                buff.append("<tr>");
                outputAnswerRow(buff, pa, userAnswers, "");
                buff.append("</tr>");
            }
            buff.append("</table>");

            outputResultText(buff, score);
        } else {

            if (question.getDiscriminatorValue().equals("one")) {
                outputQuestionText(buff, question);
                buff.append("<table>");
                for (PossibleAnswer pa : question.getPossibleAnswers()) {
                    buff.append("<tr>");
                    if (userAnswers.contains(pa)) // displaying the user answer, which is wrong, since we are in the "wrong answer" scenario
                        outputAnswerRow(buff, pa, userAnswers, "KO");
                    else if (pa.isCorrect())  // displaying the right answer that user should have checked
                        outputAnswerRow(buff, pa, userAnswers, "OK");
                    else // wrong answers not chosen by user
                        outputAnswerRow(buff, pa, userAnswers, "");
                    buff.append("</tr>");
                }
                buff.append("</table>");
            } else { // multi
                outputQuestionText(buff, question);
                buff.append("<table>");
                for (PossibleAnswer pa : question.getPossibleAnswers()) {
                    buff.append("<tr>");
                    if ((userAnswers.contains(pa) && pa.isCorrect()) || (!userAnswers.contains(pa) && !pa.isCorrect()))
                        outputAnswerRow(buff, pa, userAnswers, "OK");
                    else
                        outputAnswerRow(buff, pa, userAnswers, "KO");
                    buff.append("</tr>");
                }
                buff.append("</table>");
            }
            outputResultText(buff, score);
        }
    }

    private void outputQuestionText(StringBuilder buff, Question question) {

        buff.append("<p class=\"question\">").append("<b>Question : </b>").append(question.getText()).append("</p>");
    }

    private void outputResultText(StringBuilder buff, double score) {

        buff.append("<p class=\"result\">").append("<b>Result : </b>").append((score < 1.0) ? "KO" : "OK").append(", <b>Score : </b>").append(score).append("</p>");
    }

    private void outputAnswerRow(StringBuilder buff, PossibleAnswer pa, List<PossibleAnswer> userAnswers, String result) {

        buff.append("<td>").append(result).append("</td>");
        buff.append("<td>").append("<input type=\"checkbox\" disabled=\"true\"").append((userAnswers.contains(pa) ? " checked " : "")).append("\"/>").append("</td>");
        buff.append("<td>").append(pa.getText()).append("</td>");
    }
}
