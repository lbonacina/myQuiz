package myQuiz.model.quiz.runner;

import myQuiz.model.quiz.Question;
import myQuiz.model.quiz.Quiz;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public class SimpleQuizRunner implements QuizRunner<Quiz, Question> {

    private Quiz quiz;
    private List<Question> questionList;
    private int position;

    public SimpleQuizRunner(Quiz quiz) {

        this.quiz = quiz;
        this.questionList = quiz.getQuestions();
        position = 0;
    }

    @Override
    public boolean hasNextQuestion() {

        return !(position >= questionList.size() - 1);
    }

    @Override
    public void nextQuestion() {

        if (!hasNextQuestion())
            throw new NoSuchElementException();
        position += 1;
    }

    @Override
    public boolean hasPreviousQuestion() {

        return !(position <= 0);
    }

    @Override
    public void previousQuestion() {

        if (!hasPreviousQuestion())
            throw new NoSuchElementException();
        position -= 1;
    }

    @Override
    public Question currentQuestion() {

        return questionList.get(position);
    }

    @Override
    public int currentQuestionIndex() {

        return position;
    }

    @Override
    public int questionsCount() {

        return questionList.size();
    }

    @Override
    public double score() {

        return quiz.score();
    }

}
