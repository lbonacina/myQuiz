package myQuiz.model.quiz.runner;

import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;

import java.util.Collections;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public class RandomQuizRunner extends SimpleQuizRunner {

    public RandomQuizRunner(Quiz quiz) {

        this.quiz = quiz;
        Collections.shuffle(quiz.getQuestions());
        questionList = quiz.getQuestions();
        for (Question q : questionList)
            q.setSubmitted(true);
        position = 0;
    }

    @Override
    public int questionsCount() {

        return questionList.size();
    }

}
