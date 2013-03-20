package myQuiz.controller.session;

import myQuiz.model.session.Session;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
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
@ConversationScoped
@ConversationGroup(SessionConversationQualifier.class)
public class SessionsListController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -6015402483174483083L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject QuizService quizService;
    @Inject SessionService sessionService;

    List<Session> sessions;
    Session selectedSession;

    @Inject private Event<Session> sessionEvent;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        log.debug("SessionListController::init");
        sessions = sessionService.findAll();
    }


    public void onSessionListChanged(@Observes final Session session) {

        log.debug("refresh session list");
        sessions = sessionService.findAll();
    }

    public String newSession() {

        selectedSession = new Session();
        return "edit?faces-redirect=true";
    }

    public String editSession() {

        if (!selectedSession.getStatus().equals(Session.SessionStatus.NEW)) {
            Messages.addGlobalError("cannotEditOpenOrClosedSession");
            return "";
        }

        if (selectedSession != null)
            return "edit?faces-redirect=true";

        Messages.addGlobalError("noSessionSelected");
        return "";
    }

    // TODO : session should open "itself" automatically on start date
    public void openSession() {

        if (!selectedSession.getStatus().equals(Session.SessionStatus.NEW)) {
            Messages.addGlobalError("cannotOpenSession");
            return;
        }

        if (selectedSession == null) {
            Messages.addGlobalError("noSessionSelected");
            return;
        }

        sessionService.open(selectedSession);
        sessionEvent.fire(selectedSession);
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
