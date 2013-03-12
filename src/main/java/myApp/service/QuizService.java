package myApp.service;

import com.mysema.query.BooleanBuilder;
import myApp.model.quiz.Quiz;
import myApp.model.user.QUser;
import myApp.model.user.Role;
import myApp.model.user.User;
import myApp.repository.QuizRepository;
import myApp.repository.RoleRepository;
import myApp.repository.UserConstraintException;
import myApp.repository.UserRepository;
import myApp.util.AppLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

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
public class QuizService implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 2332677310929733841L;

    @Inject @AppLog
    private Logger log ;

    @Inject QuizRepository quizRepository ;

// -------------------------- OTHER METHODS --------------------------

    public Quiz findById(Long id) {
        return quizRepository.findOne(id) ;
    }

}
