package myQuiz.repository;

import myQuiz.model.session.Session;
import myQuiz.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;


/**
 * User: eluibon
 * Date: 04/01/13
 * Time: 16.17
 */
public interface SessionRepository extends JpaRepository<Session, Long>, QueryDslPredicateExecutor {

    @Query("select s.user from Submission s where s.session = ?1")
    List<User> findAllSubscriptions(Session session);
}
