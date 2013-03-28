package myQuiz.service;

import myQuiz.util.AppLog;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

/**
 * User: eluibon
 * Date: 11/12/12
 * Time: 14.06
 */
@Stateless
public class SchedulerService implements Serializable {

    // ------------------------------ FIELDS ------------------------------
    private static final long serialVersionUID = 1433006510080113404L;


    @Inject @AppLog private Logger log;

    //@Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", persistent = true)
    public void backgroundProcessing() {

        log.info("\n\n\t SchedulerService's backgroundProcessing() called....at: " + new Date());
    }

}
