package myQuiz.model.quiz.runner;

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
public class RandomSubsetQuestionCycler {

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


    public boolean hasNext() {

        return !(position >= questionList.size() - 1);
    }


    public void next() {

        if (!hasNext())
            throw new NoSuchElementException();
        position += 1;
    }


    public boolean hasPrevious() {

        return !(position <= 0);
    }


    public void previous() {

        if (!hasPrevious())
            throw new NoSuchElementException();
        position -= 1;
    }


    public Question current() {

        return questionList.get(position);
    }


    public int currentSeqIndex() {

        return position + 1;
    }


    public int currentMaxIndex() {

        return size;
    }
}
