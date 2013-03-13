package myQuiz.controller.quiz;

import myQuiz.model.quiz.Quiz;
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
@Named("usersubs_ctrl")
@ViewAccessScoped
public class UserSubsController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 6783905787434942322L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    @Inject @ConversationGroup(SubmissionConversationQualifier.class)
    SubmissionController quizController;

    List<Submission> userQuiz;

    Submission selectedSubmission;

// -------------------------- OTHER METHODS --------------------------

    public void createTestQuizSubmission() {

        Quiz quiz = quizService.findById((long) 1);
        quizService.createQuizSubmissionsForUser(quiz, user);
        userQuiz = quizService.findQuizSubmissionsForUser(user);
    }

    public String startQuiz() {

        quizController.start(selectedSubmission);
        return "submission?faces-redirect=true";
    }

    @PostConstruct
    public void init() {

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
