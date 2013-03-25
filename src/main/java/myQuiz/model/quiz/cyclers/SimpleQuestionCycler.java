package myQuiz.model.quiz.cyclers;

import myQuiz.model.quiz.Question;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public class SimpleQuestionCycler implements Cycler<Question> {

    private List<Question> questionList;
    private int position;

    public SimpleQuestionCycler(List<Question> questionList) {

        this.questionList = questionList;
        position = 0;
    }

    @Override
    public boolean hasNext() {

        return !(position >= questionList.size() - 1);
    }

    @Override
    public void next() {

        if (!hasNext())
            throw new NoSuchElementException();
        position += 1;
    }

    @Override
    public boolean hasPrevious() {

        return !(position <= 0);
    }

    @Override
    public void previous() {

        if (!hasPrevious())
            throw new NoSuchElementException();
        position -= 1;
    }

    @Override
    public Question current() {

        return questionList.get(position);
    }

    @Override
    public int currentSeqIndex() {

        return position + 1;
    }

    @Override
    public int currentMaxIndex() {

        return questionList.size();
    }
}
