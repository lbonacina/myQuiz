package myApp.service;

import myApp.model.quiz.Quiz;
import myApp.repository.QuizRepository;
import myApp.util.AppLog;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

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

// -------------------------- OTHER METHODS --------------------------

    public Quiz findById(Long id) {
        return quizRepository.findOne(id);
    }

}
