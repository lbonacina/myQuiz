package myQuiz.controller.quiz;

import myQuiz.model.quiz.PossibleAnswer;
import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Submission;
import myQuiz.model.user.User;
import myQuiz.security.LoggedUser;
import myQuiz.service.QuizService;
import myQuiz.util.AppLog;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.Conversation;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * controls the executions of one quiz by the user (the submission)
 */
@Named("sub_ctrl")
@ConversationScoped
@ConversationGroup(SubmissionConversationQualifier.class)
public class SubmissionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    static final long serialVersionUID = 8277824266030751108L;

    @Inject Conversation conversation;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    Quiz quiz;
    Submission submission;

    int currentQuestionNumber;

    PossibleAnswer singleUserAnswer;
    List<PossibleAnswer> multiUserAnswer;

    MultiValueMap userAnswers;

    double finalScore;
    boolean quizComplete;

// -------------------------- OTHER METHODS --------------------------

    public void start(Submission userSubmission) {

        submission = userSubmission;
        quiz = submission.getQuiz();
        submission.start();
        quizService.saveQuizSubmission(submission);
    }

    public String complete() {

        userAnswers.remove(getCurrentQuestion());
        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.putAll(getCurrentQuestion(), multiUserAnswer);
        } else {
            userAnswers.put(getCurrentQuestion(), singleUserAnswer);
        }

        quizComplete = true;
        log.debug("User Answers = ");
        for (Object q : userAnswers.keySet()) {
            submission.registerAnswers((Question) q, (List<PossibleAnswer>) userAnswers.get(q));

            log.debug("Question : {}", q.toString());
            for (Object p : (List<PossibleAnswer>) userAnswers.get(q)) {
                log.debug("Answer : {}", p);
            }
        }

        finalScore = submission.complete();
        quizService.saveQuizSubmission(submission);

        return "results?faces-redirect=true";
    }

    public List<PossibleAnswer> getCurrentPossibleAnswers() {
        return quiz.getNthQuestion(currentQuestionNumber).getPossibleAnswers();
    }

    public boolean getIsEndOfQuiz() {
        return (currentQuestionNumber == quiz.getNumberOfQuestions());
    }

    public boolean getIsStartOfQuiz() {
        return (currentQuestionNumber == 1);
    }

    @PostConstruct
    public void init() {

        log.debug("SubmissionController::init");
        userAnswers = MultiValueMap.decorate(new HashMap<Question, PossibleAnswer>(), ArrayList.class);
        currentQuestionNumber = 1;
        finalScore = 0.0;
        quizComplete = false;
    }

    public void next() {

        log.debug("current question : {} , going to next question.", currentQuestionNumber);

        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.putAll(getCurrentQuestion(), multiUserAnswer);
        } else {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.put(getCurrentQuestion(), singleUserAnswer);
        }

        if (currentQuestionNumber < quiz.getNumberOfQuestions()) {
            currentQuestionNumber += 1;

            if (isCurrentQuestionMultiAnswer()) {
                singleUserAnswer = null;
                multiUserAnswer = (List<PossibleAnswer>) userAnswers.get(getCurrentQuestion());
            } else {
                List<PossibleAnswer> list = (List<PossibleAnswer>) userAnswers.get(getCurrentQuestion());
                if ((list != null) && (list.size() > 0))
                    singleUserAnswer = list.get(0);
                else
                    singleUserAnswer = null;

                multiUserAnswer = null;
            }
        }
    }

    public void prev() {

        log.debug("current question : {} , going to prev question.", currentQuestionNumber);

        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.putAll(getCurrentQuestion(), multiUserAnswer);
        } else {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.put(getCurrentQuestion(), singleUserAnswer);
        }

        if (currentQuestionNumber > 1) {
            currentQuestionNumber -= 1;

            if (isCurrentQuestionMultiAnswer()) {
                singleUserAnswer = null;
                multiUserAnswer = (List<PossibleAnswer>) userAnswers.get(getCurrentQuestion());
            } else {
                List<PossibleAnswer> list = (List<PossibleAnswer>) userAnswers.get(getCurrentQuestion());
                if ((list != null) && (list.size() > 0))
                    singleUserAnswer = list.get(0);
                else
                    singleUserAnswer = null;

                multiUserAnswer = null;
            }
        }
    }

    public boolean isCurrentQuestionMultiAnswer() {
        return getCurrentQuestion().getDiscriminatorValue().equals("multi");
    }

    public Question getCurrentQuestion() {
        return quiz.getNthQuestion(currentQuestionNumber);
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public List<PossibleAnswer> getMultiUserAnswer() {
        return multiUserAnswer;
    }

    public void setMultiUserAnswer(List<PossibleAnswer> multiUserAnswer) {
        this.multiUserAnswer = multiUserAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public PossibleAnswer getSingleUserAnswer() {
        return singleUserAnswer;
    }

    public void setSingleUserAnswer(PossibleAnswer singleUserAnswer) {
        this.singleUserAnswer = singleUserAnswer;
    }

    public boolean isQuizComplete() {
        return quizComplete;
    }

    public void setQuizComplete(boolean quizComplete) {
        this.quizComplete = quizComplete;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
