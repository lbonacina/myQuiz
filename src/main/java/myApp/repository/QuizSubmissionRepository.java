package myApp.repository;

import myApp.model.quiz.QuizSubmission;
import myApp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;


/**
 * User: eluibon
 * Date: 04/01/13
 * Time: 16.17
 */
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long>, QueryDslPredicateExecutor {

    public List<QuizSubmission> findQuizSubmissionByUser(User user);
}
