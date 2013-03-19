package myQuiz.controller.quiz;

import myQuiz.model.quiz.Session;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * controls the executions of one quiz by the user (the submission)
 */
@Named("sl_ctrl")
@ViewAccessScoped
public class SessionsListController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -6015402483174483083L;


    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject QuizService quizService;
    @Inject SessionService sessionService;

    List<Session> sessions;
    Session selectedSession;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        sessions = sessionService.findAll();
    }

    public String newSession() {

        selectedSession = new Session();
        return "edit?faces-redirect=true";
    }

    public String editSession() {

        // TODO : cannot edit a Session that is already started, so we need to check if currentDate >= startDate
        // session edit might delete all submissions of the users and recreate them
        if (selectedSession != null)
            return "edit?faces-redirect=true";

        // TODO error on missing selection
        return "";
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    @Produces
    @SelectedSession
    public Session getSelectedSession() {
        return selectedSession;
    }

    public void setSelectedSession(Session selectedSession) {
        this.selectedSession = selectedSession;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
