package myQuiz.model.quiz.runner;

import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;

import java.util.Collections;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public class RandomSubsetQuizRunner extends SimpleQuizRunner {

    protected int size;

    public RandomSubsetQuizRunner(Quiz quiz, int subsetSize) {

        this.quiz = quiz;
        Collections.shuffle(quiz.getQuestions());
        questionList = quiz.getQuestions().subList(0, subsetSize);
        for (Question q : questionList)
            q.setSubmitted(true);
        position = 0;
        size = subsetSize;
    }

    @Override
    public int questionsCount() {

        return size;
    }

}
