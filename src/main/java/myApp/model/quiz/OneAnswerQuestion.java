package myApp.model.quiz;

import org.apache.commons.collections.CollectionUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.56
 */
@Entity
@DiscriminatorValue("one")
public class OneAnswerQuestion extends Question {

    public OneAnswerQuestion() {
        super();
    }

    public OneAnswerQuestion(String text, List<PossibleAnswer> answers) {
        super(text);
        assert answers != null ;
        assert answers.size() > 0 ;
        assert CollectionUtils.countMatches(answers, new CorrectPredicate()) == 1 ;
        possibleAnswers = new ArrayList<PossibleAnswer>(answers) ;
    }

    @Override
    public double score(List<PossibleAnswer> userAnswers) {
        assert userAnswers.size() == 1 ;
        return (userAnswers.get(0).correct) ? 1.0 : 0.0 ;
    }
}
