package myQuiz.controller.quiz;

import myQuiz.model.quiz.Answer;
import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Submission;
import myQuiz.model.quiz.cyclers.Cycler;
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
 * controls the executions of one quiz by the user (the submission)
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

    Quiz quiz;
    Submission submission;
    Cycler<Question> questionsCycler;

    Answer singleUserAnswer;
    List<Answer> multiUserAnswer;

    MultiValueMap userAnswers;

    double finalScore;
    boolean quizComplete;

// -------------------------- OTHER METHODS --------------------------

    public String complete() {

        userAnswers.remove(getCurrentQuestion());
        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.putAll(getCurrentQuestion(), multiUserAnswer);
        }
        else {
            userAnswers.put(getCurrentQuestion(), singleUserAnswer);
        }

        quizComplete = true;

        log.debug("User Answers = ");
        for (Question question : quiz.getQuestions()) {
            List<Answer> answers = (List<Answer>) userAnswers.get(question);

            if (answers != null) {
                for (Answer a : answers) {
                    a.mark();
                    log.debug("Ans : " + a);
                }
            }
        }

        finalScore = submission.complete();
        submission.setReport(generateXMLReport());
        quizService.saveQuizSubmission(submission);
        resultsController.setSubmission(submission);
        return "results?faces-redirect=true";
    }

    public boolean isCurrentQuestionMultiAnswer() {

        return questionsCycler.current().getDiscriminatorValue().equals("multi");
    }

    private String generateXMLReport() {

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
            log.debug("Error generating XML report :", e);  //To change body of catch statement use File | Settings | File Templates.
            return "ERROR";
        }
    }

    public List<Answer> getCurrentPossibleAnswers() {

        return questionsCycler.current().getAnswers();
    }

    public boolean getIsEndOfQuiz() {

        return !questionsCycler.hasNext();
    }

    public boolean getIsStartOfQuiz() {

        return !questionsCycler.hasPrevious();
    }

    @PostConstruct
    public void init() {

        log.debug("SubmissionController::init");
        userAnswers = MultiValueMap.decorate(new HashMap<Question, Answer>(), ArrayList.class);
        finalScore = 0.0;
        quizComplete = false;
    }

    public void next() {

        storeResultsForCurrentQuestion();
        questionsCycler.next();
        retrieveResultsForCurrentQuestion();
    }

    // take the results from the inputs (based on the type of the query)
    // and store it in the multimap
    private void storeResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.putAll(getCurrentQuestion(), multiUserAnswer);
        }
        else {
            userAnswers.remove(getCurrentQuestion());
            userAnswers.put(getCurrentQuestion(), singleUserAnswer);
        }
    }


    // retrive the results of the question from the multimap and store it
    // in the appropriate values based on question type
    private void retrieveResultsForCurrentQuestion() {

        if (isCurrentQuestionMultiAnswer()) {
            singleUserAnswer = null;
            multiUserAnswer = (List<Answer>) userAnswers.get(getCurrentQuestion());
        }
        else {
            List<Answer> list = (List<Answer>) userAnswers.get(getCurrentQuestion());
            singleUserAnswer = ((list != null) && (list.size() > 0)) ? list.get(0) : null;
            multiUserAnswer = null;
        }
    }

    public void prev() {

        storeResultsForCurrentQuestion();
        questionsCycler.previous();
        retrieveResultsForCurrentQuestion();
    }

    public void start(Submission userSubmission) {

        submission = userSubmission;
        quiz = submission.getQuiz();
        submission.start();
        quizService.saveQuizSubmission(submission);
        questionsCycler = quiz.cycler();
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Question getCurrentQuestion() {

        return questionsCycler.current();
    }

    public int getCurrentQuestionNumber() {

        return questionsCycler.currentSeqIndex();
    }

    public int getQuestionsNumber() {

        return questionsCycler.currentMaxIndex();
    }

    public double getFinalScore() {

        return finalScore;
    }

    public void setFinalScore(int finalScore) {

        this.finalScore = finalScore;
    }

    public List<Answer> getMultiUserAnswer() {

        return multiUserAnswer;
    }

    public void setMultiUserAnswer(List<Answer> multiUserAnswer) {

        this.multiUserAnswer = multiUserAnswer;
    }

    public Quiz getQuiz() {

        return quiz;
    }

    public void setQuiz(Quiz quiz) {

        this.quiz = quiz;
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
