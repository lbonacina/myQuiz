package myQuiz.security.accesslog;

import myQuiz.model.accesslog.AccessLogEntry;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 19/02/13
 * Time: 17.06
 * Default empty implementation of AccessLog
 */
public class DefaultAccessLog implements AccessLog, Serializable {

    private static final long serialVersionUID = -3708044878145913754L;

    @Override
    public void trackSuccessfulLogin(String username, String fullUserName) {
    }

    @Override
    public void trackFailedLogin(String username, AccessLogEntry.Reason reason) {
    }

    @Override
    public void trackSuccessfulLogout(String username, String fullUserName) {
    }

    @Override
    public void trackSessionExpiration(String username, String fullUserName) {
    }
}
