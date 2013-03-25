package myQuiz.test.quiz;

import junit.framework.Assert;
import myQuiz.model.quiz.*;
import myQuiz.model.quiz.cyclers.Cycler;
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
        Cycler<Question> iter = myQuiz.cycler();

        Assert.assertFalse(iter.hasPrevious());
        iter.previous();
    }

    @Test
    public void nextAtStartTest() {

        Quiz myQuiz = submission.getQuiz();
        Cycler<Question> iter = myQuiz.cycler();
        Assert.assertEquals("question_1", iter.current().getText());
        Assert.assertTrue(iter.hasNext());
        iter.next();
        Assert.assertEquals("question_2", iter.current().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextNextNextTest() {

        Quiz myQuiz = submission.getQuiz();
        Cycler<Question> iter = myQuiz.cycler();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals("question_1", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_2", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_3", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_4", iter.current().getText());
        Assert.assertFalse(iter.hasNext());
        iter.next();
    }

    @Test
    public void nextAndPreviousAlternatedTest() {

        Quiz myQuiz = submission.getQuiz();
        Cycler<Question> iter = myQuiz.cycler();
        Assert.assertTrue(iter.hasNext());
        iter.next();
        Assert.assertEquals("question_2", iter.current().getText());
        iter.previous();
        Assert.assertEquals("question_1", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_2", iter.current().getText());
        iter.previous();
        Assert.assertEquals("question_1", iter.current().getText());
    }

    @Test
    public void fullTest() {

        Quiz myQuiz = submission.getQuiz();
        Cycler<Question> iter = myQuiz.cycler();
        Assert.assertTrue(iter.hasNext());
        Assert.assertFalse(iter.hasPrevious());
        Assert.assertEquals("question_1", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_2", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_3", iter.current().getText());
        iter.next();
        Assert.assertEquals("question_4", iter.current().getText());
        Assert.assertFalse(iter.hasNext());
        iter.previous();
        Assert.assertEquals("question_3", iter.current().getText());
        iter.previous();
        Assert.assertEquals("question_2", iter.current().getText());
        iter.previous();
        Assert.assertEquals("question_1", iter.current().getText());
        Assert.assertFalse(iter.hasPrevious());
    }
}
