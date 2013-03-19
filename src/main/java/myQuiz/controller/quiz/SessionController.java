package myQuiz.controller.quiz;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.user.User;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.service.UserService;
import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


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

    private List<Quiz> quizList;
    private List<User> usersList;

    @NotNull
    private Quiz selectedQuiz;
    @NotEmpty
    private String sessionName;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

    private DualListModel<User> usersPickList;


// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void init() {

        log.info("init");

        quizList = quizService.findAll();
        usersList = userService.findAll();
        usersPickList = new DualListModel<User>();
        usersPickList.setSource(usersList);
        selectedQuiz = null;
        sessionName = null;
        startDate = null;
        endDate = null;
    }

    public void save() {

        log.info("quiz = {}", selectedQuiz);
        log.info("startDate = {}", startDate);
        log.info("endDate = {}", endDate);
        log.info("source = {}", usersPickList.getSource());
        log.info("target = {}", usersPickList.getTarget());

        for (User u : usersPickList.getTarget()) {
            log.info("user : {}", u.getFullName());
        }
        sessionService.createNew(sessionName, selectedQuiz, startDate, endDate, new HashSet<User>(usersPickList.getTarget()));
    }

// --------------------- GETTER / SETTER METHODS ---------------------


    public DualListModel<User> getUsersPickList() {
        return usersPickList;
    }

    public void setUsersPickList(DualListModel<User> usersPickList) {
        this.usersPickList = usersPickList;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Quiz getSelectedQuiz() {
        return selectedQuiz;
    }

    public void setSelectedQuiz(Quiz selectedQuiz) {
        this.selectedQuiz = selectedQuiz;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
