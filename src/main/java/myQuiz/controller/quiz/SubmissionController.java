package myQuiz.controller.quiz;

import myQuiz.model.quiz.Answer;
import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Submission;
import myQuiz.model.quiz.runner.QuizRunner;
import myQuiz.model.quiz.runner.RandomSubsetQuizRunner;
import myQuiz.model.user.User;
import myQuiz.security.LoggedUser;
import myQuiz.service.QuizService;
import myQuiz.util.AppLog;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * controls the executions of one quiz by the user through the QuizRunner interface
 */
@Named("sub_ctrl")
@ConversationScoped
@ConversationGroup(SubmissionConversationQualifier.class)
public class SubmissionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    static final long serialVersionUID = 8277824266030751108L;

    @Inject @ConversationGroup(SubmissionConversationQualifier.class)
    ResultsController resultsController;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    Submission submission;
    QuizRunner<Quiz, Question> quizRunner;

    Answer singleUserAnswer;
    List<Answer> multiUserAnswer;

    MultiValueMap userAnswers;

    boolean quizComplete;

// -------------------------- OTHER METHODS --------------------------

    public void start(Submission userSubmission) {

        submission = userSubmission;
        submission.start();
        quizService.saveQuizSubmission(submission);
        quizRunner = new RandomSubsetQuizRunner(userSubmission.getQuiz(), 5);
    }

    public String complete() {

        userAnswers.remove(quizRunner.currentQuestion());
        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.putAll(quizRunner.currentQuestion(), multiUserAnswer);
        }
        else {
            userAnswers.put(quizRunner.currentQuestion(), singleUserAnswer);
        }

        quizComplete = true;

        // TODO : this code is still bad, maybe we can avoid the multimap and write the result directly to the runnur
        // or even better code a custom JSF component to manage the Question directly (a bit complicated...)
        log.debug("User Answers = ");
        for (Question question : submission.getQuiz().getQuestions()) {
            List<Answer> answers = (List<Answer>) userAnswers.get(question);

            if (answers != null) {
                for (Answer a : answers) {
                    a.mark();
                    log.debug("Ans : " + a);
                }
            }
        }

        submission.complete();
        submission.setFinalScore(quizRunner.score());
        submission.setReport(generateReport());
        quizService.saveQuizSubmission(submission);
        resultsController.setSubmission(submission);
        return "results?faces-redirect=true";
    }

    public boolean isCurrentQuestionMultiAnswer() {

        return quizRunner.currentQuestion().getDiscriminatorValue().equals("multi");
    }

    public String generateReport() {

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Submission.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(submission, sw);
            return sw.getBuffer().toString();
        }
        catch (JAXBException e) {
            e.printStackTrace();
            return "ERROR : " + e.getMessage();
        }
    }


    public boolean getIsEndOfQuiz() {

        return !quizRunner.hasNextQuestion();
    }

    public boolean getIsStartOfQuiz() {

        return !quizRunner.hasPreviousQuestion();
    }

    @PostConstruct
    public void init() {

        log.debug("SubmissionController::init");
        userAnswers = MultiValueMap.decorate(new HashMap<Question, Answer>(), ArrayList.class);
        quizComplete = false;
    }

    public void next() {

        storeResultsForCurrentQuestion();
        quizRunner.nextQuestion();
        retrieveResultsForCurrentQuestion();
    }

    // take the results from the inputs (based on the type of the query)
    // and store it in the multimap
    private void storeResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.remove(quizRunner.currentQuestion());
            userAnswers.putAll(quizRunner.currentQuestion(), multiUserAnswer);
        }
        else {
            userAnswers.remove(quizRunner.currentQuestion());
            userAnswers.put(quizRunner.currentQuestion(), singleUserAnswer);
        }
    }


    // retrive the results of the question from the multimap and store it
    // in the appropriate values based on question type
    private void retrieveResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            singleUserAnswer = null;
            multiUserAnswer = (List<Answer>) userAnswers.get(quizRunner.currentQuestion());
        }
        else {
            List<Answer> list = (List<Answer>) userAnswers.get(quizRunner.currentQuestion());
            singleUserAnswer = ((list != null) && (list.size() > 0)) ? list.get(0) : null;
            multiUserAnswer = null;
        }
    }

    public void prev() {

        storeResultsForCurrentQuestion();
        quizRunner.previousQuestion();
        retrieveResultsForCurrentQuestion();
    }


// --------------------- GETTER / SETTER METHODS ---------------------


    public QuizRunner<Quiz, Question> getQuizRunner() {

        return quizRunner;
    }

    public void setQuizRunner(QuizRunner<Quiz, Question> quizRunner) {

        this.quizRunner = quizRunner;
    }

    public List<Answer> getMultiUserAnswer() {

        return multiUserAnswer;
    }

    public void setMultiUserAnswer(List<Answer> multiUserAnswer) {

        this.multiUserAnswer = multiUserAnswer;
    }

    public Answer getSingleUserAnswer() {

        return singleUserAnswer;
    }

    public void setSingleUserAnswer(Answer singleUserAnswer) {

        this.singleUserAnswer = singleUserAnswer;
    }

    public Submission getSubmission() {

        return submission;
    }

    public void setSubmission(Submission submission) {

        this.submission = submission;
    }

    public boolean isQuizComplete() {

        return quizComplete;
    }

    public void setQuizComplete(boolean quizComplete) {

        this.quizComplete = quizComplete;
    }
}
