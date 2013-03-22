package myQuiz.controller.quiz;

import myQuiz.model.quiz.Submission;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.transform.TransformerException;
import java.io.Serializable;


/**
 * controls the executions of one quiz by the user (the submission)
 */
@Named("res_ctrl")
@ConversationScoped
@ConversationGroup(SubmissionConversationQualifier.class)
public class ResultsController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 8073400677597002720L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;

    Submission submission;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        log.debug("ResultsController::init");
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

// --------------------- GETTER / SETTER METHODS ---------------------

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
