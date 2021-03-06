package myQuiz.test.quiz;

import junit.framework.Assert;
import myQuiz.model.quiz.*;
import myQuiz.model.session.Session;
import myQuiz.model.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.51
 */
public class OneAnswerQuizTest {

    Submission submission;

    @Before
    public void initTests() {

        List<Answer> q1list = new ArrayList<Answer>();
        q1list.add(new Answer("answer_1", true));
        q1list.add(new Answer("answer_2", false));
        q1list.add(new Answer("answer_3", false));
        Question q1 = new OneAnswerQuestion("question_1", q1list);

        List<Answer> q2list = new ArrayList<Answer>();
        q2list.add(new Answer("answer_1", false));
        q2list.add(new Answer("answer_2", true));
        q2list.add(new Answer("answer_3", false));
        Question q2 = new OneAnswerQuestion("question_2", q2list);

        List<Answer> q3list = new ArrayList<Answer>();
        q3list.add(new Answer("answer_1", true));
        q3list.add(new Answer("answer_2", false));
        q3list.add(new Answer("answer_3", false));
        Question q3 = new OneAnswerQuestion("question_3", q3list);

        List<Answer> q4list = new ArrayList<Answer>();
        q4list.add(new Answer("answer_1", false));
        q4list.add(new Answer("answer_2", false));
        q4list.add(new Answer("answer_3", true));
        Question q4 = new OneAnswerQuestion("question_4", q4list);

        Quiz quiz = new Quiz("SimpleQuiz");

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);

        // q1 = 1, q2 = 2, q3 = 1, q4 = 3

        User user = new User();

        Session session = new Session("test", quiz, null, null);
        submission = new Submission(user, session);
    }

    @Test
    public void allCorrectTest() {

        Quiz myQuiz = submission.getQuiz();

        myQuiz.getNthQuestion(1).getNthAnswer(1).mark();
        myQuiz.getNthQuestion(2).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(3).getNthAnswer(1).mark();
        myQuiz.getNthQuestion(4).getNthAnswer(3).mark();

        Assert.assertEquals(4.0, myQuiz.score());
    }

    @Test
    public void allWrongTest() {

        Quiz myQuiz = submission.getQuiz();

        myQuiz.getNthQuestion(1).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(2).getNthAnswer(1).mark();
        myQuiz.getNthQuestion(3).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(4).getNthAnswer(2).mark();

        Assert.assertEquals(0.0, myQuiz.score());
    }

    @Test
    public void oneOutOfFourTest() {

        Quiz myQuiz = submission.getQuiz();

        myQuiz.getNthQuestion(1).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(2).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(3).getNthAnswer(2).mark();
        myQuiz.getNthQuestion(4).getNthAnswer(2).mark();

        Assert.assertEquals(1.0, myQuiz.score());
    }
}
