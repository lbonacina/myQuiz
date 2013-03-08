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
@DiscriminatorValue("multi")
public class MultiAnswerQuestion extends Question {

    public MultiAnswerQuestion() {
        super();
    }

    public MultiAnswerQuestion(String text, List<PossibleAnswer> answers) {
        super(text);
        assert answers != null ;
        assert answers.size() > 0 ;
        assert CollectionUtils.countMatches(answers, new CorrectPredicate()) > 0 ; // at least one correct answer
        possibleAnswers = new ArrayList<PossibleAnswer>(answers) ;
    }

    @Override
    public double score(List<PossibleAnswer> userAnswers) {

        double scoreAcc = 0.0 ;
        double singleAnswerScore = 1.0 / possibleAnswers.size() ;
        for (PossibleAnswer pa : possibleAnswers) {

            if ( pa.correct ) {
                scoreAcc += (userAnswers.contains(pa)) ? singleAnswerScore : 0.0 ;
            }
            else {
                scoreAcc += (!userAnswers.contains(pa)) ? singleAnswerScore : 0.0 ;
            }
        }
        return scoreAcc ;
    }

}
