package myQuiz.controller.quiz;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Session;
import myQuiz.model.user.User;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.service.UserService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * controls the executions of one quiz by the user (the submission)
 */
@Named("sess_ctrl")
@ViewAccessScoped
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

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        quizList = quizService.findAll();

        usersPickList = new DualListModel<User>();

        List<User> allUsers = userService.findAll();
        Set<User> subscribers = session.getSubscribers();
        allUsers.removeAll(subscribers);
        usersPickList.setSource(allUsers);
        usersPickList.setTarget(new ArrayList<User>(subscribers));
    }

    public void save() {

        session.getSubscribers().clear();
        session.setSubscribers(new HashSet<User>(usersPickList.getTarget()));
        sessionService.save(session);
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
