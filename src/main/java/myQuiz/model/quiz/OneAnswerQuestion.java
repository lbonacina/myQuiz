package myQuiz.model.quiz;

import org.apache.commons.collections.CollectionUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.56
 */
@Entity
@DiscriminatorValue("one")
public class OneAnswerQuestion extends Question implements Serializable {

    private static final long serialVersionUID = -5984300901950933341L;

    public OneAnswerQuestion() {
        super();
    }

    public OneAnswerQuestion(String text, List<Answer> answers) {
        super(text, answers);
        assert answers != null;
        assert answers.size() > 0;
        assert CollectionUtils.countMatches(answers, new CorrectPredicate()) == 1;
    }

    @Override
    public double score() {

        for (Answer answer : answers) {
            if (answer.correct && answer.checked)
                return 1.0;
        }

        return 0.0;
    }
}
