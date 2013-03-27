package myQuiz.controller;

import myQuiz.model.accesslog.AccessLogEntry;
import myQuiz.model.user.User;
import myQuiz.repository.UserConstraintException;
import myQuiz.security.SecurityProducer;
import myQuiz.security.accesslog.AccessLog;
import myQuiz.security.accesslog.AppAccessLog;
import myQuiz.service.QuizService;
import myQuiz.service.SessionService;
import myQuiz.service.UserService;
import myQuiz.util.AppLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@Named("guest_ctrl")
@RequestScoped
public class GuestController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -1320641733409582746L;

    @Inject FacesContext facesContext;

    @Inject @AppLog Logger log;
    @Inject @AppAccessLog AccessLog accessLog;

    @NotEmpty
    String firstName;
    @NotEmpty
    String lastName;
    @NotEmpty @Email
    String email;

    @Inject UserService userService;
    @Inject QuizService quizService;
    @Inject SessionService sessionService;

// -------------------------- OTHER METHODS --------------------------

    private Subject subject = SecurityUtils.getSubject();

    /**
     * get username & password from Shiro PassThruAuthenticationFilter and authenticate the user
     * also set in session the full name of the subject to display it (subject is normally the username)
     *
     * @return navigation
     */
    public String login() {

        // TODO : login as guest could be simplified, but we maybe need to allow for a user to guest multiple time with the same email

        log.debug("trying to guest with {}/{}", firstName + " " + lastName, email);

        AccessLogEntry.Reason reason = AccessLogEntry.Reason.NONE;

        User user = null;
        try {
            user = userService.createGuest(firstName, lastName, email);
        }
        catch (UserConstraintException uce) {
            log.error("Unexpected Exception while logging in user.", uce.getMessage());
            Messages.addGlobalError("email_unique");
            return "";
        }

        sessionService.addGuestToDummySession(user);

        String username = user.getUsername();
        String password = user.getDecryptedPassword();

        log.debug("guest created : {}", user);

        boolean isAuth = subject.isAuthenticated();

        if (!isAuth) {

            log.debug("{} is not authenticated", username);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                subject.login(token);
                subject.getSession().setAttribute(SecurityProducer.SUBJECT_FULL_NAME_SESSION_KEY, user.getFullName());
                isAuth = true;
            }
            catch (UnknownAccountException uae) {
                log.debug("Username not found");
                reason = AccessLogEntry.Reason.UNKNOWN_USERNAME;
            }
            catch (IncorrectCredentialsException ice) {
                // password is wrong but username is right, need to track on DB the number of tries
                int count = userService.trackFailedLoginAttempt(username);
                log.debug("Password is wrong. {} tries left", count);
                reason = AccessLogEntry.Reason.WRONG_PASSWORD;
            }
            catch (LockedAccountException lae) {
                log.debug("Account is locked");
                reason = AccessLogEntry.Reason.ACCOUNT_LOCKED;
            }
            catch (DisabledAccountException dae) {
                log.debug("Account is disabled");
                reason = AccessLogEntry.Reason.ACCOUNT_DISABLED;
            }
            catch (AuthenticationException ae) {
                log.debug("Error during login", ae);
                reason = AccessLogEntry.Reason.GENERIC_EXCEPTION;
            }
            catch (Exception e) {
                log.error("Unexpected Exception while logging in user.", e);
                Messages.addGlobalError("unexpectedError", e.getMessage());
                reason = AccessLogEntry.Reason.UNCATCHED_EXCEPTION;
            }
        }
        else {

            log.debug("{} already authenticated.", username);
            user = userService.findByUsername(username); // really needed apart from satisfying the assert below ?
        }

        if (isAuth) {

            accessLog.trackSuccessfulLogin(username, user.getFullName());

            assert user != null;
            log.debug("Redirecting to submission page.");
            return "pages/submission/list?faces-redirect=true";
        }
        else {

            Messages.addGlobalError("genericLoginError");
            accessLog.trackFailedLogin(username, reason);
            return null;
        }
    }


// --------------------- GETTER / SETTER METHODS ---------------------


    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}
