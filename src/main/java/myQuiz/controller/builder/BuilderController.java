package myQuiz.controller.builder;

import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import myQuiz.service.QuestionService;
import myQuiz.service.QuizService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * controls the executions of one submission by the user (the submission)
 */
@Named("builder_ctrl")
@ConversationScoped
@ConversationGroup(BuilderConversationQualifier.class)
public class BuilderController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -1103051988492114201L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;

    @Inject QuestionService questionService;
    @Inject QuizService quizService;

    List<Question> questions;
    List<Question.Level> questionLevels;

    Question.Level selectedQuestionLevel;
    int questionCount;
    String selectedQuestionArea;

    Quiz quiz;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        questions = questionService.findAll();
        questionLevels = Arrays.asList(Question.Level.values());
        newQuiz();
    }

    public void newQuiz() {

        quiz = new Quiz();
    }

    public void addAnswers() {

        List<Question> list = new ArrayList<Question>();
        list.addAll(getRandomQuestions(selectedQuestionArea, selectedQuestionLevel, questionCount));
        log.debug("Area = {}, Level = {}", selectedQuestionArea, selectedQuestionLevel);
        log.debug("Count = {}, List = {}", questionCount, list);
        quiz.addQuestions(list);
    }

    public String saveQuiz() {

        quizService.save(quiz);
        return "quiz?faces-redirect=true";
    }

    private List<Question> getRandomQuestions(String area, Question.Level level, int numer) {

        List<Question> list = new ArrayList<Question>();

        for (Question q : questions) {
            if (q.getArea().equals(area) && q.getLevel().equals(level)) {
                list.add(q);
            }
        }

        List list2 = list.subList(0, numer);

        Collections.shuffle(list2);

        return list2;
    }

    public String load() {

        quiz = quizService.findById((long) 5);
        return "quiz?faces-redirect=true";
    }

// --------------------- GETTER / SETTER METHODS ---------------------


    public String getSelectedQuestionArea() {

        return selectedQuestionArea;
    }

    public void setSelectedQuestionArea(String selectedQuestionArea) {

        this.selectedQuestionArea = selectedQuestionArea;
    }

    public int getQuestionCount() {

        return questionCount;
    }

    public void setQuestionCount(int questionCount) {

        this.questionCount = questionCount;
    }

    public List<Question.Level> getQuestionLevels() {

        return questionLevels;
    }

    public void setQuestionLevels(List<Question.Level> questionLevels) {

        this.questionLevels = questionLevels;
    }

    public List<Question> getQuestions() {

        return questions;
    }

    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }

    public Quiz getQuiz() {

        return quiz;
    }

    public void setQuiz(Quiz quiz) {

        this.quiz = quiz;
    }

    public Question.Level getSelectedQuestionLevel() {

        return selectedQuestionLevel;
    }

    public void setSelectedQuestionLevel(Question.Level selectedQuestionLevel) {

        this.selectedQuestionLevel = selectedQuestionLevel;
    }
}
