package myQuiz.controller.session;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.session.Session;
import myQuiz.model.user.User;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.service.UserService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;


/**
 * controls the executions of one submission by the user (the submission)
 */
@Named("sess_ctrl")
@ConversationScoped
@ConversationGroup(SessionConversationQualifier.class)
public class SessionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -3852364777972311495L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject QuizService quizService;
    @Inject UserService userService;
    @Inject SessionService sessionService;

    @Inject @SelectedSession Session session;

    private List<Quiz> quizList;

    private DualListModel<User> usersPickList;

    @Inject private Event<Session> sessionEvent;


// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        log.debug("SessionController::init");

        quizList = quizService.findAll();

        usersPickList = new DualListModel<User>();

        List<User> allUsers = userService.findAll();
        Set<User> subscribers = session.getSubscribers();
        allUsers.removeAll(subscribers);
        usersPickList.setSource(allUsers);
        usersPickList.setTarget(new ArrayList<User>(subscribers));
    }

    public String save() {

        boolean isValid = true;
        if (session.getStartDate().before(Calendar.getInstance().getTime())) {
            Messages.addError("session_form:startDate", "startDateMustBeAfterToday");
            isValid = false;
        }
        if (session.getEndDate().before(session.getStartDate())) {
            Messages.addError("session_form:endDate", "endDateMustBeAfterStartDate");
            isValid = false;
        }
        if ((usersPickList.getTarget() == null) || (usersPickList.getTarget().size() == 0)) {
            Messages.addError("session_form:subscribers", "mustHaveAtLeastOneSubscriber");
            isValid = false;
        }

        if (isValid) {
            session.getSubscribers().clear();
            session.setSubscribers(new HashSet<User>(usersPickList.getTarget()));
            sessionService.save(session);
            sessionEvent.fire(session);
        }

        return (isValid) ? "list?faces-redirect=true" : "";
    }

// --------------------- GETTER / SETTER METHODS ---------------------


    public DualListModel<User> getUsersPickList() {

        return usersPickList;
    }

    public void setUsersPickList(DualListModel<User> usersPickList) {

        this.usersPickList = usersPickList;
    }

    public List<Quiz> getQuizList() {

        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {

        this.quizList = quizList;
    }

    public Session getSession() {

        return session;
    }

    public void setSession(Session session) {

        this.session = session;
    }
}
