package myApp.repository;

import myApp.model.quiz.Quiz;
import myApp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import javax.persistence.QueryHint;


/**
 * User: eluibon
 * Date: 04/01/13
 * Time: 16.17
 */
public interface QuizRepository extends JpaRepository<Quiz, Long>, QueryDslPredicateExecutor {
}
