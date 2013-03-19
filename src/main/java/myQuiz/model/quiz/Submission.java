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

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_timestamp")
    private Date startTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_timestamp")
    private Date endTimestamp;

    @Column(name = "final_score")
    private Double finalScore;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_session")
    private Session session;

    // Answers are not saved right now, only total score
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "quiz_submission_id")
    @Transient
    private List<Answer> userAnswers;

// --------------------------- CONSTRUCTORS ---------------------------

    public Submission() {
        userAnswers = new ArrayList<Answer>();
    }

    public Submission(User user, Session session) {
        status = Status.NEW;
        this.user = user;
        this.session = session;
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
        startTimestamp = Calendar.getInstance().getTime();
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

        endTimestamp = Calendar.getInstance().getTime();
        status = Status.COMPLETED;

        return finalScore;
    }

    public Quiz getQuiz() {
        return session.getQuiz();
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

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startDate) {
        this.startTimestamp = startDate;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endDate) {
        this.endTimestamp = endDate;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
