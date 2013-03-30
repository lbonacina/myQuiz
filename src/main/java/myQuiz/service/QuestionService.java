package myQuiz.service;

import com.mysema.query.BooleanBuilder;
import myQuiz.model.quiz.QQuestion;
import myQuiz.model.quiz.Question;
import myQuiz.repository.QuestionRepository;
import myQuiz.util.AppLog;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    public Question findById(Long id) {

        return questionRepository.findOne(id);
    }

    public List<Question> findByFilters(Map<String, String> filters) {

        QQuestion question = QQuestion.question;
        BooleanBuilder bb = new BooleanBuilder();

        String areaValue = filters.get("area");
        String levelValue = filters.get("level");


        if ((areaValue != null) && (!areaValue.equals("") && (!areaValue.equals("empty")))) {
            bb.and(question.area.like(areaValue));
        }
        if ((levelValue != null) && (!levelValue.equals("") && (!levelValue.equals("empty")))) {
            Question.Level level = Question.Level.valueOf(levelValue);
            bb.and(question.level.eq(level));
        }

        return (List<Question>) questionRepository.findAll(bb.getValue());
    }

}
