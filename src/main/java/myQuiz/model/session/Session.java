package myQuiz.model.session;

import myQuiz.model.quiz.Quiz;
import myQuiz.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.24
 */
@Entity
@Table(name = "session")
public class Session implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 4775639456110933500L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 4000)
    String name;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private SessionStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_quiz", referencedColumnName = "id", nullable = true)
    @NotNull
    private Quiz quiz;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    @NotNull
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    @NotNull
    private Date endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_session",
            joinColumns = @JoinColumn(name = "id_session"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<User> subscribers;

// --------------------------- CONSTRUCTORS ---------------------------

    public Session() {
        status = SessionStatus.NEW;
        subscribers = new HashSet<User>();
    }

    public Session(String name, Quiz quiz, Date startDate, Date endDate) {
        status = SessionStatus.NEW;
        this.name = name;
        this.quiz = quiz;
        this.startDate = startDate;
        this.endDate = endDate;
        subscribers = new HashSet<User>();
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> users) {
        this.subscribers = users;
    }

// -------------------------- ENUMERATIONS --------------------------

    public enum SessionStatus {
        NEW, OPEN, CLOSED
    }
}
