package myQuiz.service;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.quiz.Submission;
import myQuiz.model.user.User;
import myQuiz.repository.QuizRepository;
import myQuiz.repository.SubmissionRepository;
import myQuiz.util.AppLog;
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
    @Inject SubmissionRepository quizSubmissionRepository;

// -------------------------- OTHER METHODS --------------------------

    public List<Quiz> findAll() {

        return quizRepository.findAll();
    }

    public Quiz findById(Long id) {

        return quizRepository.findOne(id);
    }

    public void saveQuizSubmission(Submission submission) {

        quizSubmissionRepository.save(submission);
    }

    public List<Submission> findQuizSubmissionsForUser(User user) {

        return quizSubmissionRepository.findSubmissionsByUser(user);
    }

    public void save(Quiz quiz) {

        quizRepository.save(quiz);
    }

}
