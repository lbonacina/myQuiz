package myQuiz.controller.quiz;

import myQuiz.model.quiz.Session;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {

        sessions = sessionService.findAll();
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
