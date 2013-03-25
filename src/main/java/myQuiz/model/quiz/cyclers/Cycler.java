package myQuiz.model.quiz.cyclers;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public interface Cycler<T> {

    boolean hasNext();

    boolean hasPrevious();

    void next();

    void previous();

    T current();

    int currentSeqIndex();

    int currentMaxIndex();

}
