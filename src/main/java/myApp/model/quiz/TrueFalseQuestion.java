package myApp.model.quiz;

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
@DiscriminatorValue("bool")
public class TrueFalseQuestion extends OneAnswerQuestion {

    public TrueFalseQuestion() {
        super();
    }

    public TrueFalseQuestion(String text, boolean correctAnswer) {
        super();
        setText(text);
        List<PossibleAnswer> possibleAnswers = new ArrayList<PossibleAnswer>() ;
        possibleAnswers.add(new PossibleAnswer("True",correctAnswer)) ;
        possibleAnswers.add(new PossibleAnswer("False",!correctAnswer)) ;
        setPossibleAnswers(possibleAnswers);
    }
}
