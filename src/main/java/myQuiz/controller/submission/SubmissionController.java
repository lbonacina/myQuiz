package myQuiz.controller.submission;

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
import javax.xml.transform.TransformerException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;


/**
 * controls the executions of one submission by the user through the QuizRunner interface
 */
@Named("sub_ctrl")
@ConversationScoped
@ConversationGroup(SubmissionConversationQualifier.class)
public class SubmissionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    static final long serialVersionUID = 8277824266030751108L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    List<Submission> userSubmissions;
    Submission submission;
    QuizRunner<Quiz, Question> quizRunner;

    Answer singleUserAnswer;
    List<Answer> multiUserAnswer;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        log.debug("SubmissionController::init");
        userSubmissions = quizService.findQuizSubmissionsForUser(user);
    }

    public String start() {

        submission.start();
        quizService.saveQuizSubmission(submission);
        quizRunner = new RandomSubsetQuizRunner(submission.getQuiz(), 5);
        return "submission?faces-redirect=true";
    }

    public String complete() {

        retrieveResultsForCurrentQuestion();
        submission.complete();
        submission.setFinalScore(quizRunner.score());
        submission.setReport(generateXmlReport());
        quizService.saveQuizSubmission(submission);
        return "results?faces-redirect=true";
    }

    public String generateXmlReport() {

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

    public String getHtmlReport() {

        try {
            return submission.generateHTMLReport();
        }
        catch (TransformerException e) {
            log.debug("Error during conversion from XML to HTML", e);
            return ("<html><body>Sorry, error :(</body></html>");
        }
    }

    public void next() {

        storeResultsForCurrentQuestion();
        quizRunner.nextQuestion();
        retrieveResultsForCurrentQuestion();
    }

    public void prev() {

        storeResultsForCurrentQuestion();
        quizRunner.previousQuestion();
        retrieveResultsForCurrentQuestion();
    }

    public boolean getIsEndOfQuiz() {

        return !quizRunner.hasNextQuestion();
    }

    public boolean getIsStartOfQuiz() {

        return !quizRunner.hasPreviousQuestion();
    }

    public boolean isCurrentQuestionMultiAnswer() {

        return quizRunner.currentQuestion().getDiscriminatorValue().equals("multi");
    }

    private void storeResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            if ((multiUserAnswer != null) && (multiUserAnswer.size() > 0)) {
                quizRunner.storeAnswers(multiUserAnswer);
            }
        }
        else {
            if (singleUserAnswer != null)
                quizRunner.storeAnswer(singleUserAnswer);
        }
    }

    private void retrieveResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            singleUserAnswer = null;
            multiUserAnswer = quizRunner.retrieveAnswers();
        }
        else {
            List<Answer> list = quizRunner.retrieveAnswers();
            singleUserAnswer = ((list != null) && (list.size() > 0)) ? list.get(0) : null;
            multiUserAnswer = null;
        }
    }


// --------------------- GETTER / SETTER METHODS ---------------------

    public List<Submission> getUserSubmissions() {

        return userSubmissions;
    }

    public void setUserSubmissions(List<Submission> userSubmissions) {

        this.userSubmissions = userSubmissions;
    }

    public QuizRunner<Quiz, Question> getQuizRunner() {

        return quizRunner;
    }

    public void setQuizRunner(QuizRunner<Quiz, Question> quizRunner) {

        this.quizRunner = quizRunner;
    }

    public Answer getSingleUserAnswer() {

        return singleUserAnswer;
    }

    public void setSingleUserAnswer(Answer singleUserAnswer) {

        this.singleUserAnswer = singleUserAnswer;
    }

    public List<Answer> getMultiUserAnswer() {

        return multiUserAnswer;
    }

    public void setMultiUserAnswer(List<Answer> multiUserAnswer) {

        this.multiUserAnswer = multiUserAnswer;
    }

    public Submission getSubmission() {

        return submission;
    }

    public void setSubmission(Submission submission) {

        this.submission = submission;
    }
}
