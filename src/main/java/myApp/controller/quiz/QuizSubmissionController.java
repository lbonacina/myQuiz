package myApp.controller.quiz;

import myApp.model.quiz.*;
import myApp.model.user.User;
import myApp.security.LoggedUser;
import myApp.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Named("quizsub_ctrl")
@ConversationScoped
@ConversationGroup(QuizSubmissionConversationQualifier.class)
public class QuizSubmissionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    static final long serialVersionUID = 8277824266030751108L;

    @Inject FacesContext facesContext;

    @Inject @AppLog Logger log;

    @Inject @LoggedUser User user ;

    Quiz quiz ;
    QuizSubmission quizSubmission ;
    int currentQuestionNumber ;
    Question currentQuestion ;
    List<PossibleAnswer> currentQuestionPossibleAnswers ;
    PossibleAnswer userAnswer ;

    double finalScore ;
    boolean quizComplete ;

    @PostConstruct
    public void init() {

        List<PossibleAnswer> q1list = new ArrayList<PossibleAnswer>() ;
        q1list.add(new PossibleAnswer("answer_1",true));
        q1list.add(new PossibleAnswer("answer_2",false));
        q1list.add(new PossibleAnswer("answer_3",false));
        Question q1 = new OneAnswerQuestion("question_1",q1list) ;

        List<PossibleAnswer> q2list = new ArrayList<PossibleAnswer>() ;
        q2list.add(new PossibleAnswer("answer_1",false));
        q2list.add(new PossibleAnswer("answer_2",true));
        q2list.add(new PossibleAnswer("answer_3",false));
        Question q2 = new OneAnswerQuestion("question_2",q2list) ;

        List<PossibleAnswer> q3list = new ArrayList<PossibleAnswer>() ;
        q3list.add(new PossibleAnswer("answer_1",true));
        q3list.add(new PossibleAnswer("answer_2",false));
        q3list.add(new PossibleAnswer("answer_3",false));
        Question q3 = new OneAnswerQuestion("question_3",q3list) ;

        Question q4 = new TrueFalseQuestion("question_1",true) ;
        Question q5 = new TrueFalseQuestion("question_2",false) ;

        quiz = new Quiz("SimpleQuiz") ;
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
        quiz.addQuestion(q5);

        quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        currentQuestionNumber = 1 ;
        currentQuestion = quiz.getNthQuestion(currentQuestionNumber) ;
        currentQuestionPossibleAnswers = currentQuestion.getPossibleAnswers() ;
        userAnswer = null ;
        finalScore = 0.0 ;
        quizComplete = false ;
    }


    public void prev() {

        log.debug("current question : {} , going to prev question.",currentQuestion);

        // record user anwser
        log.debug("answer to question {} : {}", currentQuestion, userAnswer);
        quizSubmission.registerAnswer(currentQuestion,userAnswer);

        if (currentQuestionNumber > 1) {

            currentQuestionNumber -= 1 ;
            currentQuestion = quiz.getNthQuestion(currentQuestionNumber) ;
            currentQuestionPossibleAnswers = currentQuestion.getPossibleAnswers() ;
            userAnswer = null ;
        }

        log.debug("new current question : {}", currentQuestion.toString());
    }

    public void next() {

        log.debug("current question : {} , going to next question.",currentQuestion);

        // record user anwser
        log.debug("answer to question {} : {}", currentQuestion, userAnswer);
        quizSubmission.registerAnswer(currentQuestion,userAnswer);

        if (currentQuestionNumber < quiz.getNumberOfQuestions() ) {

            currentQuestionNumber += 1 ;
            currentQuestion = quiz.getNthQuestion(currentQuestionNumber) ;
            currentQuestionPossibleAnswers = currentQuestion.getPossibleAnswers() ;
            userAnswer = null ;
        }

        log.debug("new current question : {}", currentQuestion.toString());
    }

    public boolean getIsStartOfQuiz() {
        return (currentQuestionNumber == 1) ;
    }

    public boolean getIsEndOfQuiz() {
        return (currentQuestionNumber == quiz.getNumberOfQuestions()) ;
    }

    public void complete() {

        quizComplete = true ;
        finalScore = quizSubmission.score() ;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public QuizSubmission getQuizSubmission() {
        return quizSubmission;
    }

    public void setQuizSubmission(QuizSubmission quizSubmission) {
        this.quizSubmission = quizSubmission;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public List<PossibleAnswer> getCurrentQuestionPossibleAnswers() {
        return currentQuestionPossibleAnswers;
    }

    public void setCurrentQuestionPossibleAnswers(List<PossibleAnswer> currentQuestionPossibleAnswers) {
        this.currentQuestionPossibleAnswers = currentQuestionPossibleAnswers;
    }

    public PossibleAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(PossibleAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public boolean isQuizComplete() {
        return quizComplete;
    }

    public void setQuizComplete(boolean quizComplete) {
        this.quizComplete = quizComplete;
    }
}
