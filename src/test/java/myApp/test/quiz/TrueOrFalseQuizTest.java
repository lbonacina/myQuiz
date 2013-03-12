package myApp.test.quiz;

import junit.framework.Assert;
import myApp.model.quiz.*;
import myApp.model.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.51
 */
public class TrueOrFalseQuizTest {

    Quiz quiz;

    @Before
    public void initTests() {

        Question q1 = new TrueFalseQuestion("question_1",true) ;
        Question q2 = new TrueFalseQuestion("question_2",false) ;
        Question q3 = new TrueFalseQuestion("question_3",false) ;
        Question q4 = new TrueFalseQuestion("question_4",true) ;
        quiz = new Quiz("SimpleQuiz") ;
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
    }

    @Test
    public void allCorrectTest() {

        User user = new User() ;

        QuizSubmission quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        Question q ;
        q = quiz.getNthQuestion(1) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(2) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(3) ;
        quizSubmission.registerAnswer(q, q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(4) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));

        Assert.assertEquals(4.0, quizSubmission.score());
    }

    @Test
    public void allWrongTest() {

        User user = new User() ;

        QuizSubmission quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        Question q ;
        q = quiz.getNthQuestion(1) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(2) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(3) ;
        quizSubmission.registerAnswer(q, q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(4) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));

        Assert.assertEquals(0.0, quizSubmission.score());
    }

    @Test
    // TFFT
    public void oneOutOfFourTest1() {

        User user = new User() ;

        QuizSubmission quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        Question q ;
        q = quiz.getNthQuestion(1) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(2) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(3) ;
        quizSubmission.registerAnswer(q, q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(4) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));

        Assert.assertEquals(1.0, quizSubmission.score());
    }

    @Test
    // TFFT
    public void oneOutOfFourTest2() {

        User user = new User() ;

        QuizSubmission quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        Question q ;
        q = quiz.getNthQuestion(1) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(2) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(1));
        q = quiz.getNthQuestion(3) ;
        quizSubmission.registerAnswer(q, q.getNthPossibleAnswer(2));
        q = quiz.getNthQuestion(4) ;
        quizSubmission.registerAnswer(q,q.getNthPossibleAnswer(2));

        Assert.assertEquals(1.0, quizSubmission.score());
    }
}
