package myQuiz.model.quiz.runner;

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

    double score();
}
