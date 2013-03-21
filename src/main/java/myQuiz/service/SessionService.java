package myQuiz.service;

import myQuiz.model.quiz.Submission;
import myQuiz.model.session.Session;
import myQuiz.model.user.User;
import myQuiz.repository.SessionRepository;
import myQuiz.repository.SubmissionRepository;
import myQuiz.util.AppLog;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Inject SubmissionRepository submissionRepository;

// -------------------------- OTHER METHODS --------------------------

    public List<Session> findAll() {

        Sort sort = new Sort(Sort.Direction.DESC, "startDate");
        return sessionRepository.findAll(sort);
    }

    public void save(Session session) {

        sessionRepository.save(session);
    }

    public void open(Session session) {

        session.setStatus(Session.SessionStatus.OPEN);

        Set<Submission> submissions = new HashSet<Submission>();
        for (User user : session.getSubscribers()) {
            submissions.add(new Submission(user, session));
        }

        sessionRepository.save(session);
        submissionRepository.save(submissions);
    }


    public void close(Session session) {

        session.setStatus(Session.SessionStatus.CLOSED);

        List<Submission> submissions = submissionRepository.findSubmissionsBySession(session);

        for (Submission submission : submissions) {
            submission.setStatus(Submission.SubmissionStatus.COMPLETED);
        }

        sessionRepository.save(session);
        submissionRepository.save(submissions);
    }

    public List<Submission> findSubmissionsForSession(Session session) {

        return submissionRepository.findSubmissionsBySession(session);
    }
}
