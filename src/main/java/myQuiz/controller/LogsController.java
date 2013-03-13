package myQuiz.controller;

import myQuiz.model.accesslog.AccessLogEntry;
import myQuiz.service.AccessLogEntryService;
import myQuiz.util.AppLog;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("logs_ctrl")
@RequestScoped
public class LogsController implements Serializable {

    private static final long serialVersionUID = -822414527884637683L;

// ------------------------------ FIELDS ------------------------------

    @Inject @AppLog private Logger log;
    @Inject private AccessLogEntryService accessLogEntryService;

    List<AccessLogEntry> accessLogEntryList;

    public List<AccessLogEntry> getAccessLogEntryList() {
        return accessLogEntryList;
    }

    public void setAccessLogEntryList(List<AccessLogEntry> accessLogEntryList) {
        this.accessLogEntryList = accessLogEntryList;
    }

    @PostConstruct
    public void init() {
        accessLogEntryList = accessLogEntryService.findAll();
    }
}
