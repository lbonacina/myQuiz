package myApp.service;

import myApp.model.quiz.Quiz;
import myApp.model.quiz.QuizSubmission;
import myApp.model.user.User;
import myApp.repository.QuizRepository;
import myApp.repository.QuizSubmissionRepository;
import myApp.util.AppLog;
import org.slf4j.Logger;

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
public class QuizService implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 2332677310929733841L;

    @Inject @AppLog
    private Logger log;

    @Inject QuizRepository quizRepository;
    @Inject QuizSubmissionRepository quizSubmissionRepository;

// -------------------------- OTHER METHODS --------------------------

    public Quiz findById(Long id) {
        return quizRepository.findOne(id);
    }

    public void saveQuizSubmission(QuizSubmission quizSubmission) {
/*
        assert quizSubmission.getFinalScore() != null ;
        assert quizSubmission.getUser() != null ;
        assert quizSubmission.getStartDate() != null ;
        assert quizSubmission.getEndDate() != null ;
*/
        quizSubmissionRepository.save(quizSubmission);
    }

    public List<QuizSubmission> findQuizSubmissionsForUser(User user) {

        return quizSubmissionRepository.findQuizSubmissionByUser(user);
    }

    public void createQuizSubmissionsForUser(Quiz quiz, User user) {

        quizSubmissionRepository.save(new QuizSubmission(user, quiz));
    }

}
