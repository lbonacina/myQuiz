package myQuiz.repository;

import myQuiz.model.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * User: eluibon
 * Date: 04/01/13
 * Time: 16.17
 */
public interface QuestionRepository extends JpaRepository<Question, Long>, QueryDslPredicateExecutor {

}
