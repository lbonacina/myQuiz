package myApp.controller.quiz;

import myApp.model.quiz.Quiz;
import myApp.model.quiz.QuizSubmission;
import myApp.model.user.User;
import myApp.security.LoggedUser;
import myApp.service.QuizService;
import myApp.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named("userquiz_ctrl")
@ViewAccessScoped
public class UserQuizController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 6783905787434942322L;

    @Inject FacesContext facesContext;
    @Inject @AppLog Logger log;
    @Inject @LoggedUser User user;
    @Inject QuizService quizService;

    @Inject @ConversationGroup(QuizSubmissionConversationQualifier.class)
    QuizSubmissionController quizController;

    List<QuizSubmission> userQuiz;

    QuizSubmission selectedQuizSubmission;

// -------------------------- OTHER METHODS --------------------------

    public void createTestQuizSubmission() {

        Quiz quiz = quizService.findById((long) 1);
        quizService.createQuizSubmissionsForUser(quiz, user);
        userQuiz = quizService.findQuizSubmissionsForUser(user);
    }

    public String startQuiz() {

        quizController.start(selectedQuizSubmission);
        return "question?faces-redirect=true";
    }

    @PostConstruct
    public void init() {

        userQuiz = quizService.findQuizSubmissionsForUser(user);
    }

// --------------------- GETTER / SETTER METHODS ---------------------


    public QuizSubmission getSelectedQuizSubmission() {
        return selectedQuizSubmission;
    }

    public void setSelectedQuizSubmission(QuizSubmission selectedQuizSubmission) {
        this.selectedQuizSubmission = selectedQuizSubmission;
    }

    public List<QuizSubmission> getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(List<QuizSubmission> userQuiz) {
        this.userQuiz = userQuiz;
    }
}
