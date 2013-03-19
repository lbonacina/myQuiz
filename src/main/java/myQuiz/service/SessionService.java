package myQuiz.service;

import myQuiz.model.quiz.Session;
import myQuiz.repository.SessionRepository;
import myQuiz.util.AppLog;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * User: eluibon
 * Date: 11/12/12
 * Time: 14.06
 */
@Stateless
public class SessionService implements Serializable {

    // ------------------------------ FIELDS ------------------------------
    private static final long serialVersionUID = -2022296170410565093L;

    @Inject @AppLog
    private Logger log;

    @Inject SessionRepository sessionRepository;

// -------------------------- OTHER METHODS --------------------------

    public List<Session> findAll() {

        Sort sort = new Sort(Sort.Direction.DESC, "startDate");
        return sessionRepository.findAll(sort);
    }

    public void save(Session session) {

        sessionRepository.save(session);
    }
}
