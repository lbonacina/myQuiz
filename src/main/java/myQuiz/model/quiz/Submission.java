package myQuiz.model.quiz;

import myQuiz.model.user.User;
import org.apache.commons.collections.map.MultiValueMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.24
 */
@Entity
@Table(name = "submission")
public class Submission {
// ------------------------------ FIELDS ------------------------------

    public enum Status {
        NEW, STARTED, COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = true)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_quiz", referencedColumnName = "id", nullable = true)
    @NotNull
    private Quiz quiz;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "final_score")
    private Double finalScore;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "quiz_submission_id")
    @Transient
    private List<Answer> userAnswers;

// --------------------------- CONSTRUCTORS ---------------------------

    public Submission() {
        userAnswers = new ArrayList<Answer>();
    }

    public Submission(User user, Quiz quiz) {
        status = Status.NEW;
        this.user = user;
        this.quiz = quiz;
        userAnswers = new ArrayList<Answer>();
    }

// -------------------------- OTHER METHODS --------------------------

    public void registerAnswer(Question question, PossibleAnswer answer) {
        userAnswers.add(new Answer(question, answer));
    }

    public void registerAnswers(Question question, List<PossibleAnswer> answers) {
        for (PossibleAnswer p : answers)
            userAnswers.add(new Answer(question, p));
    }

    public void start() {
        status = Status.STARTED;
        startDate = Calendar.getInstance().getTime();
    }

    public double complete() {

        double score = 0.0;

        Map<Question, PossibleAnswer> answerMap = new HashMap<Question, PossibleAnswer>();
        MultiValueMap mvm = MultiValueMap.decorate(answerMap, ArrayList.class);

        for (Answer answer : userAnswers) {
            mvm.put(answer.getQuestion(), answer.getAnswer());
        }

        for (Iterator iter = mvm.keySet().iterator(); iter.hasNext(); ) {

            Question q = (Question) iter.next();
            List<PossibleAnswer> pa = (List<PossibleAnswer>) mvm.get(q);
            score += q.score(pa);
        }

        finalScore = Math.round(score * 100.0) / 100.0;

        endDate = Calendar.getInstance().getTime();
        status = Status.COMPLETED;

        return finalScore;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
