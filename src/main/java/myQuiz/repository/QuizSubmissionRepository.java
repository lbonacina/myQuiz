package myQuiz.repository;

import myQuiz.model.quiz.Submission;
import myQuiz.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;


/**
 * User: eluibon
 * Date: 04/01/13
 * Time: 16.17
 */
public interface QuizSubmissionRepository extends JpaRepository<Submission, Long>, QueryDslPredicateExecutor {

    public List<Submission> findQuizSubmissionByUser(User user);
}
