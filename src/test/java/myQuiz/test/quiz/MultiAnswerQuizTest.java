package myQuiz.test.quiz;

import junit.framework.Assert;
import myQuiz.model.quiz.*;
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
public class MultiAnswerQuizTest {

    Quiz quiz;

    @Before
    public void initTests() {

        List<PossibleAnswer> q1list = new ArrayList<PossibleAnswer>();
        q1list.add(new PossibleAnswer("answer_1", true));
        q1list.add(new PossibleAnswer("answer_2", true));
        q1list.add(new PossibleAnswer("answer_3", false));
        Question q1 = new MultiAnswerQuestion("question_1", q1list);

        List<PossibleAnswer> q2list = new ArrayList<PossibleAnswer>();
        q2list.add(new PossibleAnswer("answer_1", false));
        q2list.add(new PossibleAnswer("answer_2", true));
        q2list.add(new PossibleAnswer("answer_3", false));
        Question q2 = new MultiAnswerQuestion("question_2", q2list);

        List<PossibleAnswer> q3list = new ArrayList<PossibleAnswer>();
        q3list.add(new PossibleAnswer("answer_1", true));
        q3list.add(new PossibleAnswer("answer_2", true));
        q3list.add(new PossibleAnswer("answer_3", true));
        Question q3 = new MultiAnswerQuestion("question_3", q3list);

        List<PossibleAnswer> q4list = new ArrayList<PossibleAnswer>();
        q4list.add(new PossibleAnswer("answer_1", false));
        q4list.add(new PossibleAnswer("answer_2", false));
        q4list.add(new PossibleAnswer("answer_3", true));
        Question q4 = new MultiAnswerQuestion("question_4", q4list);

        quiz = new Quiz("SimpleQuiz");

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);

        // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    }

    @Test
    // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    public void allCorrectTest() {

        User user = new User();

        Submission submission = new Submission(user, quiz);

        Question q;
        q = quiz.getNthQuestion(1);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));
        submission.registerAnswer(q, q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(2);
        submission.registerAnswer(q, q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(3);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));
        submission.registerAnswer(q, q.getNthPossibleAnswer(2));
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(4);
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));

        Assert.assertEquals(4.0, submission.complete());
    }

    @Test
    // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    public void allWrongTest() {

        User user = new User();

        Submission submission = new Submission(user, quiz);

        Question q;
        q = quiz.getNthQuestion(1);
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(2);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(3);
        q = quiz.getNthQuestion(4);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));
        submission.registerAnswer(q, q.getNthPossibleAnswer(2));

        Assert.assertEquals(0.0, submission.complete());
    }

    @Test
    // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    public void oneOutOfFourTest() {

        User user = new User();

        Submission submission = new Submission(user, quiz);

        Question q;
        q = quiz.getNthQuestion(1);
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(2);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(3);
        q = quiz.getNthQuestion(4);
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));

        Assert.assertEquals(1.0, submission.complete());
    }

    @Test
    // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    public void partialAnswersTest1() {

        User user = new User();

        Submission submission = new Submission(user, quiz);

        Question q;
        q = quiz.getNthQuestion(1);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));  // only one out of 2, .33 point
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(2);
        submission.registerAnswer(q, q.getNthPossibleAnswer(2)); // ok 1 point
        q = quiz.getNthQuestion(3);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1)); // 2 out of three, .66 point
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(4);
        submission.registerAnswer(q, q.getNthPossibleAnswer(2)); // 1 out of three (the first is false), 0.33

        Assert.assertEquals(2.33, submission.complete());
    }

    @Test
    // q1 = 1,2, q2 = 2, q3 = 1,2,3, q4 = 3
    public void partialAnswersTest2() {

        User user = new User();

        Submission submission = new Submission(user, quiz);

        Question q;
        q = quiz.getNthQuestion(1);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1));  // only one out of 2, .33 point
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(2);
        submission.registerAnswer(q, q.getNthPossibleAnswer(2)); // ok 1 point
        q = quiz.getNthQuestion(3);
        submission.registerAnswer(q, q.getNthPossibleAnswer(1)); // 2 out of three, .66 point
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));
        q = quiz.getNthQuestion(4);
        submission.registerAnswer(q, q.getNthPossibleAnswer(2)); // 2 out of three (the first is false), 0.66
        submission.registerAnswer(q, q.getNthPossibleAnswer(3));

        Assert.assertEquals(2.67, submission.complete());
    }
}
