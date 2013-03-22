package myQuiz.controller.quiz;

import myQuiz.model.quiz.Submission;
import myQuiz.model.user.User;
import myQuiz.security.LoggedUser;
import myQuiz.service.QuizService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * shows all of the submission for the logged user, with status and score
 */
@Named("subslist_ctrl")
@ViewAccessScoped
public class SubmissionsListController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 6783905787434942322L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    @Inject @ConversationGroup(SubmissionConversationQualifier.class)
    SubmissionController submissionController;

    @Inject @ConversationGroup(SubmissionConversationQualifier.class)
    ResultsController resultsController;

    List<Submission> userQuiz;

    Submission selectedSubmission;

    // -------------------------- OTHER METHODS --------------------------

    public String startQuiz() {

        submissionController.start(selectedSubmission);
        return "submission?faces-redirect=true";
    }

    public String showResults() {

        resultsController.setSubmission(selectedSubmission);
        return "results?faces-redirect=true";
    }


    @PostConstruct
    public void init() {

        log.debug("SubmissionsListController::init");
        userQuiz = quizService.findQuizSubmissionsForUser(user);
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Submission getSelectedSubmission() {
        return selectedSubmission;
    }

    public void setSelectedSubmission(Submission selectedSubmission) {
        this.selectedSubmission = selectedSubmission;
    }

    public List<Submission> getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(List<Submission> userQuiz) {
        this.userQuiz = userQuiz;
    }
}
