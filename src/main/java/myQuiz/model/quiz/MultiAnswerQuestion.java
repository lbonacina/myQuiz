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
@DiscriminatorValue("multi")
public class MultiAnswerQuestion extends Question implements Serializable {

    private static final long serialVersionUID = -7314490433719448775L;

    public MultiAnswerQuestion() {
        super();
    }

    public MultiAnswerQuestion(String text, List<Answer> answers) {
        super(text, answers);
        assert answers != null;
        assert answers.size() > 0;
        assert CollectionUtils.countMatches(answers, new CorrectPredicate()) > 0; // at least one correct answer
    }

    @Override
    public double score() {

        double scoreAcc = 0.0;
        double singleAnswerScore = 1.0 / answers.size();

        for (Answer pa : answers) {

            if ((pa.correct && pa.checked) || (!pa.correct && !pa.checked))
                scoreAcc += singleAnswerScore;
        }
        return scoreAcc;
    }

}
