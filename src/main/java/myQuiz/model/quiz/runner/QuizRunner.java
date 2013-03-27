package myQuiz.model.quiz.runner;

import myQuiz.model.quiz.Answer;

import java.util.List;

/**
 * User: eluibon
 * Date: 24/03/13
 * Time: 21.45
 */
public interface QuizRunner<T, Q> {

    boolean hasNextQuestion();

    boolean hasPreviousQuestion();

    void nextQuestion();

    void previousQuestion();

    Q currentQuestion();

    int currentQuestionIndex();

    int questionsCount();

    void storeAnswer(Answer answer);

    void storeAnswers(List<Answer> answer);

    List<Answer> retrieveAnswers();

    double score();
}
