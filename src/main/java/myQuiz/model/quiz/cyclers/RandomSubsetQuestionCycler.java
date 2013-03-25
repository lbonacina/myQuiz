package myQuiz.model.quiz.cyclers;

import myQuiz.model.quiz.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public class RandomSubsetQuestionCycler implements Cycler<Question> {

    private List<Question> questionList;
    private int position;
    private int size;

    public RandomSubsetQuestionCycler(List<Question> questionList, int subsetSize) {

        List<Question> subList = new ArrayList<Question>(questionList);
        Collections.shuffle(subList);
        this.questionList = subList.subList(0, subsetSize);
        position = 0;
        size = subsetSize;
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

        return size;
    }
}
