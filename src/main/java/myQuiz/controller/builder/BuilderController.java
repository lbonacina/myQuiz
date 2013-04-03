package myQuiz.controller.builder;

import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import myQuiz.service.QuestionService;
import myQuiz.service.QuizService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    LazyDataModel model;

    Quiz quiz;
    List<Quiz> quizList;
    boolean newQuiz;
    int questionsNumber;

    List<SelectItem> areas;
    List<SelectItem> levels;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        questions = questionService.findAll();
        model = new QuestionLazyDataModel(questionService);

        quizList = quizService.findAll();
        quiz = null;
        newQuiz = false;
        questionsNumber = 3;

        areas = new ArrayList<SelectItem>();
        areas.add(new SelectItem("empty", "select..."));
        areas.add(new SelectItem("Management", "Management"));
        areas.add(new SelectItem("Database", "Database"));
        levels = new ArrayList<SelectItem>();
        levels.add(new SelectItem("empty", "select..."));
        levels.add(new SelectItem("Assessed", "Assessed"));
        levels.add(new SelectItem("Experienced", "Experienced"));
        levels.add(new SelectItem("Senior", "Senior"));
    }

    public String newQuiz() {

        quiz = new Quiz();
        newQuiz = true;
        quiz.setName("New Test");
        return "builder?faces-redirect=true";
    }

    public String editQuiz() {

        if (quiz == null) {
            Messages.addGlobalError("Select a Quiz");
            return "";
        }
        newQuiz = false;
        return "builder?faces-redirect=true";
    }

    /*
    public void onQuestionDrop(DragDropEvent ddEvent) {

        Question q = ((Question) ddEvent.getData());

        log.debug("Drag & Drop on : {}", q);
        quiz.addQuestion(q);
    }
    */

    public void addQuestions() {

        List<Question> list = new ArrayList<Question>();
        list.addAll(getRandomQuestions(questionsNumber));
        quiz.addQuestions(list);
    }

    private List<Question> getRandomQuestions(int number) {

        List<Question> list = new ArrayList<Question>();
        list.addAll((List<Question>) model.getWrappedData());
        Collections.shuffle(list);
        return list.subList(0, number);
    }

    public void removeQuestion(Question question) {

        log.debug("remove : {}", question);
        quiz.removeQuestion(question);
    }

    public String saveQuiz() {

        log.debug("save : {}", quiz);
        quizService.save(quiz);
        return "quiz?faces-redirect=true";
    }

    public String load() {

        quiz = quizService.findById((long) 5);
        return "quiz?faces-redirect=true";
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public List<SelectItem> getAreas() {

        return areas;
    }

    public void setAreas(List<SelectItem> areas) {

        this.areas = areas;
    }

    public List<SelectItem> getLevels() {

        return levels;
    }

    public void setLevels(List<SelectItem> levels) {

        this.levels = levels;
    }

    public LazyDataModel getModel() {

        return model;
    }

    public void setModel(LazyDataModel model) {

        this.model = model;
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

    public List<Quiz> getQuizList() {

        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {

        this.quizList = quizList;
    }

    public boolean isNewQuiz() {

        return newQuiz;
    }

    public void setNewQuiz(boolean newQuiz) {

        this.newQuiz = newQuiz;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }
}
