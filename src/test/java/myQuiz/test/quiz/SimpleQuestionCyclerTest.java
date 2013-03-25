package myQuiz.test.quiz;

import junit.framework.Assert;
import myQuiz.model.quiz.*;
import myQuiz.model.quiz.runner.QuizRunner;
import myQuiz.model.quiz.runner.SimpleQuizRunner;
import myQuiz.model.session.Session;
import myQuiz.model.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.51
 */
public class SimpleQuestionCyclerTest {

    Quiz quiz;
    Submission submission;

    @Before
    public void initTests() {

        List<Answer> q1list = new ArrayList<Answer>();
        q1list.add(new Answer("answer_1", true));
        q1list.add(new Answer("answer_2", true));
        q1list.add(new Answer("answer_3", false));
        Question q1 = new MultiAnswerQuestion("question_1", q1list);

        List<Answer> q2list = new ArrayList<Answer>();
        q2list.add(new Answer("answer_1", false));
        q2list.add(new Answer("answer_2", true));
        q2list.add(new Answer("answer_3", false));
        Question q2 = new MultiAnswerQuestion("question_2", q2list);

        List<Answer> q3list = new ArrayList<Answer>();
        q3list.add(new Answer("answer_1", true));
        q3list.add(new Answer("answer_2", true));
        q3list.add(new Answer("answer_3", true));
        Question q3 = new MultiAnswerQuestion("question_3", q3list);

        List<Answer> q4list = new ArrayList<Answer>();
        q4list.add(new Answer("answer_1", false));
        q4list.add(new Answer("answer_2", false));
        q4list.add(new Answer("answer_3", true));
        Question q4 = new MultiAnswerQuestion("question_4", q4list);

        Quiz quiz = new Quiz("SimpleQuiz");

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);

        // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3

        User user = new User();

        Session session = new Session("test", quiz, null, null);
        submission = new Submission(user, session);
    }

    @Test(expected = NoSuchElementException.class)
    public void previousAtStartTest() {

        Quiz myQuiz = submission.getQuiz();
        QuizRunner<Quiz, Question> iter = new SimpleQuizRunner(myQuiz);

        Assert.assertFalse(iter.hasPreviousQuestion());
        iter.previousQuestion();
    }

    @Test
    public void nextAtStartTest() {

        Quiz myQuiz = submission.getQuiz();
        QuizRunner<Quiz, Question> iter = new SimpleQuizRunner(myQuiz);
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
        Assert.assertTrue(iter.hasNextQuestion());
        iter.nextQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextNextNextTest() {

        Quiz myQuiz = submission.getQuiz();
        QuizRunner<Quiz, Question> iter = new SimpleQuizRunner(myQuiz);
        Assert.assertTrue(iter.hasNextQuestion());
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_3", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_4", iter.currentQuestion().getText());
        Assert.assertFalse(iter.hasNextQuestion());
        iter.nextQuestion();
    }

    @Test
    public void nextAndPreviousAlternatedTest() {

        Quiz myQuiz = submission.getQuiz();
        QuizRunner<Quiz, Question> iter = new SimpleQuizRunner(myQuiz);
        Assert.assertTrue(iter.hasNextQuestion());
        iter.nextQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
        iter.previousQuestion();
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
        iter.previousQuestion();
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
    }

    @Test
    public void fullTest() {

        Quiz myQuiz = submission.getQuiz();
        QuizRunner<Quiz, Question> iter = new SimpleQuizRunner(myQuiz);
        Assert.assertTrue(iter.hasNextQuestion());
        Assert.assertFalse(iter.hasPreviousQuestion());
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_3", iter.currentQuestion().getText());
        iter.nextQuestion();
        Assert.assertEquals("question_4", iter.currentQuestion().getText());
        Assert.assertFalse(iter.hasNextQuestion());
        iter.previousQuestion();
        Assert.assertEquals("question_3", iter.currentQuestion().getText());
        iter.previousQuestion();
        Assert.assertEquals("question_2", iter.currentQuestion().getText());
        iter.previousQuestion();
        Assert.assertEquals("question_1", iter.currentQuestion().getText());
        Assert.assertFalse(iter.hasPreviousQuestion());
    }
}
