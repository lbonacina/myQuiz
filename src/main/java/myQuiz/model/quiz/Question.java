package myQuiz.model.quiz;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.56
 */
@Entity
@Table(name = "question")
@Inheritance
@DiscriminatorColumn(name = "type")
@XmlRootElement(name = "question")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"text", "discriminatorValue", "answers"})
public abstract class Question {
// ------------------------------ FIELDS ------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Size(min = 10, max = 4000)
    String text;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_question", nullable = false)
    List<Answer> answers;

    @Transient
    boolean submitted;

// --------------------------- CONSTRUCTORS ---------------------------

    protected Question() {

        submitted = false;
    }


    protected Question(String text) {

        this.text = text;
        submitted = false;
    }

    public Question(String text, List<Answer> answers) {

        this.text = text;
        this.answers = new ArrayList<Answer>(answers);
        submitted = false;
    }

    public Answer getNthAnswer(int n) {

        return answers.get(n - 1);
    }

    @Transient
    @XmlElement(name = "discriminatorValue")
    public String getDiscriminatorValue() {

        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }

    public abstract double score();

    // --------------------- GETTER / SETTER METHODS ---------------------

    @XmlTransient
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @XmlElementWrapper(name = "answers")
    @XmlElement(name = "answer")
    public List<Answer> getAnswers() {

        return answers;
    }

    public void setAnswers(List<Answer> possibleAnswers) {

        this.answers = possibleAnswers;
    }

    @XmlElement
    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    @XmlTransient
    public boolean isSubmitted() {

        return submitted;
    }

    public void setSubmitted(boolean submitted) {

        this.submitted = submitted;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!answers.equals(question.answers)) return false;
        if (!text.equals(question.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = text.hashCode();
        result = 31 * result + answers.hashCode();
        return result;
    }
}
