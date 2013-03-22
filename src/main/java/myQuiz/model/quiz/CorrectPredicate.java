package myQuiz.model.quiz;

import org.apache.commons.collections.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 07/03/13
 * Time: 8.48
 */
public class CorrectPredicate implements Predicate {

    @Override
    public boolean evaluate(Object o) {
        return ((Answer) o).correct;
    }
}
