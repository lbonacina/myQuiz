package myQuiz.model.quiz;

import myQuiz.model.session.Session;
import myQuiz.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.24
 */
@Entity
@Table(name = "submission")
@XmlRootElement(name = "submission")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"user", "status", "finalScore", "startTimestamp", "endTimestamp", "session"})
public class Submission implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 1428114395020218857L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = true)
    @NotNull
    private User user;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private SubmissionStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_timestamp")
    private Date startTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_timestamp")
    private Date endTimestamp;

    @Column(name = "final_score")
    private Double finalScore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_session")
    private Session session;

    @Column(name = "report")
    private String report;


// --------------------------- CONSTRUCTORS ---------------------------

    public Submission() {

    }

    public Submission(User user, Session session) {

        status = SubmissionStatus.NEW;
        finalScore = 0.0;
        this.user = user;
        this.session = session;
    }

// -------------------------- OTHER METHODS --------------------------

    public void complete() {

        endTimestamp = Calendar.getInstance().getTime();
        status = SubmissionStatus.COMPLETED;
    }

    public Quiz getQuiz() {

        return session.getQuiz();
    }

    public void start() {

        status = SubmissionStatus.STARTED;
        startTimestamp = Calendar.getInstance().getTime();
    }


    public String getHTMLReport() {

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(getClass().getClassLoader().getResourceAsStream("transform.xslt"));
            Transformer transformer = null;
            transformer = factory.newTransformer(xslt);

            StringWriter sw = new StringWriter();
            Source xmlSource = new StreamSource(new StringReader(report));
            Result htmlResult = new StreamResult(sw);

            transformer.transform(xmlSource, htmlResult);

            return sw.toString();
        } catch (TransformerException e) {
            return ("<html><body>Sorry, error :(</body></html>");
        }
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Date getEndTimestamp() {

        return endTimestamp;
    }

    public void setEndTimestamp(Date endDate) {

        this.endTimestamp = endDate;
    }

    public Double getFinalScore() {

        return finalScore;
    }

    public void setFinalScore(Double finalScore) {

        this.finalScore = finalScore;
    }

    @XmlTransient
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Session getSession() {

        return session;
    }

    public void setSession(Session session) {

        this.session = session;
    }

    public Date getStartTimestamp() {

        return startTimestamp;
    }

    public void setStartTimestamp(Date startDate) {

        this.startTimestamp = startDate;
    }

    public SubmissionStatus getStatus() {

        return status;
    }

    public void setStatus(SubmissionStatus status) {

        this.status = status;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    @XmlTransient
    public String getReport() {

        return report;
    }

    public void setReport(String report) {

        this.report = report;
    }


    // ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Submission that = (Submission) o;

        if (session != null ? !session.equals(that.session) : that.session != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (session != null ? session.hashCode() : 0);
        return result;
    }

// -------------------------- ENUMERATIONS --------------------------

    public enum SubmissionStatus {
        NEW, STARTED, COMPLETED
    }
}
