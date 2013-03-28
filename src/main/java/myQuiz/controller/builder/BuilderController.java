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

    String quizName;
    int assessedCount;
    int experiencedCount;
    int seniorCount;

    Quiz quiz;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        questions = questionService.findAll();
    }

    public String buildRandomQuiz() {

        quiz = new Quiz();
        quiz.setName(quizName);

        List<Question> list = new ArrayList<Question>();

        list.addAll(getRandomQuestions("Management", Question.Level.ASSESSED, assessedCount));
        list.addAll(getRandomQuestions("Management", Question.Level.EXPERIENCED, experiencedCount));
        list.addAll(getRandomQuestions("Management", Question.Level.SENIOR, seniorCount));

        quiz.setQuestions(list);

        quizService.save(quiz);

        return "quiz?faces-redirect=true";
    }

    public String load() {

        quiz = quizService.findById((long) 5);
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

    public List<Question> getQuestions() {

        return questions;
    }

    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }

    public String getQuizName() {

        return quizName;
    }

    public void setQuizName(String quizName) {

        this.quizName = quizName;
    }

    public int getAssessedCount() {

        return assessedCount;
    }

    public void setAssessedCount(int assessedCount) {

        this.assessedCount = assessedCount;
    }

    public int getExperiencedCount() {

        return experiencedCount;
    }

    public void setExperiencedCount(int experiencedCount) {

        this.experiencedCount = experiencedCount;
    }

    public int getSeniorCount() {

        return seniorCount;
    }

    public void setSeniorCount(int seniorCount) {

        this.seniorCount = seniorCount;
    }

    public Quiz getQuiz() {

        return quiz;
    }

    public void setQuiz(Quiz quiz) {

        this.quiz = quiz;
    }
}
