package myQuiz.controller.session;

import myQuiz.model.quiz.Submission;
import myQuiz.model.session.Session;
import myQuiz.service.SessionService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * controls the executions of one submission by the user (the submission)
 */
@Named("results_ctrl")
@ConversationScoped
@ConversationGroup(SessionConversationQualifier.class)
public class ResultsController implements Serializable {

// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 8661925632518337625L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject SessionService sessionService;

    @Inject @SelectedSession Session session;

    private List<Submission> submissionList;

    private Submission selectedSubmission;


// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        submissionList = sessionService.findSubmissionsForSession(session);
    }

    public List<Submission> getSubmissionList() {

        return submissionList;
    }

    public void setSubmissionList(List<Submission> submissionList) {

        this.submissionList = submissionList;
    }

    public Submission getSelectedSubmission() {
        return selectedSubmission;
    }

    public void setSelectedSubmission(Submission selectedSubmission) {
        this.selectedSubmission = selectedSubmission;
    }
}
