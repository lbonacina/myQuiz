package myQuiz.controller;

import myQuiz.util.AppLog;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowContext;
import org.slf4j.Logger;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: eluibon
 * Date: 22/08/12
 * Time: 14.56
 */
@Named("menu")
@RequestScoped
public class MenuController {

    @Inject @AppLog private Logger log;

    @Inject
    private WindowContext windowContext;


    public void resetConversations() {

        log.debug("Closing all open conversations");
        windowContext.closeConversations();
    }


}
