package myQuiz.model.quiz;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.55
 */
@Entity
@Table(name = "quiz")
@XmlRootElement(name = "submission")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "submittedQuestions"})
public class Quiz implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 4482970585403512285L;

    @NotNull
    @Size(min = 5, max = 200)
    String name;

    @Size(max = 4000)
    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "quiz_question",
            joinColumns = @JoinColumn(name = "id_quiz"),
            inverseJoinColumns = @JoinColumn(name = "id_question"))
    List<Question> questions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// --------------------------- CONSTRUCTORS ---------------------------

    public Quiz() {

        questions = new ArrayList<Question>();
    }

    public Quiz(String name) {

        this.name = name;
        questions = new ArrayList<Question>();
    }

// -------------------------- OTHER METHODS --------------------------

    @XmlElementWrapper(name = "questions")
    @XmlElement(name = "question")
    public List<Question> getSubmittedQuestions() {

        List<Question> sq = new ArrayList<Question>();
        for (Question q : questions)
            if (q.isSubmitted())
                sq.add(q);
        return sq;
    }

    public void addQuestion(Question question) {

        questions.add(question);
    }

    public void addQuestions(List<Question> questions) {

        this.questions.addAll(questions);
    }

    public void removeQuestion(Question question) {

        this.questions.remove(question);
    }

    // use it only for testing purpose, use iterator for real code (1-based)
    public Question getNthQuestion(int n) {

        return questions.get(n - 1);
    }

    // use it only for testing purpose, use iterator for real code
    public int getNumberOfQuestions() {

        return questions.size();
    }

    public double score() {

        double scoreAcc = 0.0;
        for (Question q : questions)
            scoreAcc += q.score();

        return Math.round(scoreAcc * 100.0) / 100.0;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @XmlTransient
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

    @XmlTransient
    public List<Question> getQuestions() {

        return questions;
    }

    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (name != null ? !name.equals(quiz.name) : quiz.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return name != null ? name.hashCode() : 0;
    }
}
