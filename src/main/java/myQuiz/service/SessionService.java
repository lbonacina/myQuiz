package myQuiz.service;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Session;
import myQuiz.model.quiz.Submission;
import myQuiz.model.user.User;
import myQuiz.repository.SessionRepository;
import myQuiz.repository.SubmissionRepository;
import myQuiz.util.AppLog;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
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

    public void createNew(String name, Quiz quiz, Date startDate, Date endDate, Set<User> registeredUsers) {

        Session session = new Session(name, quiz, startDate, endDate, registeredUsers);
        Set<Submission> submissions = new HashSet<Submission>();
        // create a submission for each user
        for (User user : registeredUsers) {

            Submission submission = new Submission(user, session);
            submissions.add(submission);
            submissionRepository.save(submission);
        }

        session.setSubmissions(submissions);

        // create & save the new session
        sessionRepository.save(session);
    }
}
