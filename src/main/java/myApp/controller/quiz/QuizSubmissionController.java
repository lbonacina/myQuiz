package myApp.controller.quiz;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import myApp.model.quiz.*;
import myApp.model.user.User;
import myApp.security.LoggedUser;
import myApp.util.AppLog;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;


@Named("quizsub_ctrl")
@ConversationScoped
@ConversationGroup(QuizSubmissionConversationQualifier.class)
public class QuizSubmissionController implements Serializable {
// ------------------------------ FIELDS ------------------------------

    static final long serialVersionUID = 8277824266030751108L;

    @Inject FacesContext facesContext;

    @Inject @AppLog Logger log;

    @Inject @LoggedUser User user ;

    Quiz quiz ;
    QuizSubmission quizSubmission ;

    int currentQuestionNumber ;

    PossibleAnswer singleUserAnswer ;
    List<PossibleAnswer> multiUserAnswer ;

    MultiValueMap userAnswers ;

    double finalScore ;
    boolean quizComplete ;

    @PostConstruct
    public void init() {

        List<PossibleAnswer> q1list = new ArrayList<PossibleAnswer>() ;
        q1list.add(new PossibleAnswer("answer_1",true));
        q1list.add(new PossibleAnswer("answer_2",false));
        q1list.add(new PossibleAnswer("answer_3",false));
        Question q1 = new OneAnswerQuestion("question_1",q1list) ;

        List<PossibleAnswer> q2list = new ArrayList<PossibleAnswer>() ;
        q2list.add(new PossibleAnswer("answer_1",true));
        q2list.add(new PossibleAnswer("answer_2",true));
        q2list.add(new PossibleAnswer("answer_3",false));
        Question q2 = new MultiAnswerQuestion("question_2",q2list) ;

        List<PossibleAnswer> q3list = new ArrayList<PossibleAnswer>() ;
        q3list.add(new PossibleAnswer("answer_1",true));
        q3list.add(new PossibleAnswer("answer_2",false));
        q3list.add(new PossibleAnswer("answer_3",false));
        Question q3 = new OneAnswerQuestion("question_3",q3list) ;

        Question q4 = new TrueFalseQuestion("question_1",true) ;
        Question q5 = new TrueFalseQuestion("question_2",false) ;

        quiz = new Quiz("SimpleQuiz") ;
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
        quiz.addQuestion(q5);

        quizSubmission = new QuizSubmission(user, quiz, Calendar.getInstance().getTime()) ;

        userAnswers = MultiValueMap.decorate(new HashMap<Question,PossibleAnswer>(), ArrayList.class) ;

        currentQuestionNumber = 1 ;

        finalScore = 0.0 ;
        quizComplete = false ;
    }


    public Question getCurrentQuestion() {

        return quiz.getNthQuestion(currentQuestionNumber) ;
    }

    public List<PossibleAnswer> getCurrentPossibleAnswers() {

        return quiz.getNthQuestion(currentQuestionNumber).getPossibleAnswers() ;
    }

    public boolean isCurrentQuestionMultiAnswer() {

        return getCurrentQuestion().getDiscriminatorValue().equals("multi") ;
    }


    public void prev() {

        log.debug("current question : {} , going to prev question.",currentQuestionNumber);

        if ( isCurrentQuestionMultiAnswer() ) {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.putAll(getCurrentQuestion(),multiUserAnswer) ;
        }
        else {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.put(getCurrentQuestion(),singleUserAnswer) ;
        }

        if (currentQuestionNumber > 1) {
            currentQuestionNumber -= 1 ;

            if ( isCurrentQuestionMultiAnswer() ) {
                singleUserAnswer = null ;
                multiUserAnswer = (List<PossibleAnswer>)userAnswers.get(getCurrentQuestion());
            }
            else {
                List<PossibleAnswer> list = (List<PossibleAnswer>)userAnswers.get(getCurrentQuestion()) ;
                if ( ( list != null ) && ( list.size() > 0 ) )
                    singleUserAnswer = list.get(0) ;
                else
                    singleUserAnswer = null ;

                multiUserAnswer = null;
            }
        }
   }

    public void next() {

        log.debug("current question : {} , going to next question.",currentQuestionNumber);

        if ( isCurrentQuestionMultiAnswer() ) {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.putAll(getCurrentQuestion(),multiUserAnswer) ;
        }
        else {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.put(getCurrentQuestion(),singleUserAnswer) ;
        }

        if (currentQuestionNumber < quiz.getNumberOfQuestions() ) {

            currentQuestionNumber += 1 ;

            if ( isCurrentQuestionMultiAnswer() ) {
                singleUserAnswer = null ;
                multiUserAnswer = (List<PossibleAnswer>)userAnswers.get(getCurrentQuestion());
            }
            else {
                List<PossibleAnswer> list = (List<PossibleAnswer>)userAnswers.get(getCurrentQuestion()) ;
                if ( ( list != null ) && ( list.size() > 0 ) )
                    singleUserAnswer = list.get(0) ;
                else
                    singleUserAnswer = null ;

                multiUserAnswer = null;
            }
        }
   }

    public boolean getIsStartOfQuiz() {
        return (currentQuestionNumber == 1) ;
    }

    public boolean getIsEndOfQuiz() {
        return (currentQuestionNumber == quiz.getNumberOfQuestions()) ;
    }

    public void complete() {

        if ( isCurrentQuestionMultiAnswer() ) {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.putAll(getCurrentQuestion(),multiUserAnswer) ;
        }
        else {
            userAnswers.remove(getCurrentQuestion()) ;
            userAnswers.put(getCurrentQuestion(),singleUserAnswer) ;
        }

        quizComplete = true ;
        log.debug("User Answers = ");
        for (Object q : userAnswers.keySet()) {

            quizSubmission.registerAnswers((Question)q,(List<PossibleAnswer>)userAnswers.get(q));

            log.debug("Question : {}", q.toString());
            for (Object p : (List<PossibleAnswer>)userAnswers.get(q)) {
                log.debug("Answer : {}", p);
            }
        }

        finalScore = quizSubmission.score() ;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public boolean isQuizComplete() {
        return quizComplete;
    }

    public void setQuizComplete(boolean quizComplete) {
        this.quizComplete = quizComplete;
    }

    public PossibleAnswer getSingleUserAnswer() {
        return singleUserAnswer;
    }

    public void setSingleUserAnswer(PossibleAnswer singleUserAnswer) {
        this.singleUserAnswer = singleUserAnswer;
    }

    public List<PossibleAnswer> getMultiUserAnswer() {
        return multiUserAnswer;
    }

    public void setMultiUserAnswer(List<PossibleAnswer> multiUserAnswer) {
        this.multiUserAnswer = multiUserAnswer;
    }


}
