package myQuiz.service;

import myQuiz.model.quiz.Question;
import myQuiz.repository.QuestionRepository;
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
public class QuestionService implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = -4794912444732546104L;


    @Inject @AppLog
    private Logger log;

    @Inject QuestionRepository questionRepository;
// -------------------------- OTHER METHODS --------------------------

    public List<Question> findAll() {

        return questionRepository.findAll();
    }
}
