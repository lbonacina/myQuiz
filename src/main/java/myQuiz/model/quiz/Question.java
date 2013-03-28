package myQuiz.model.quiz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@XmlType(propOrder = {"text", "discriminatorValue", "answers", "area", "level"})
public abstract class Question {
// ------------------------------ FIELDS ------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Size(min = 10, max = 4000)
    String text;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    Set<Answer> answers;

    Level level;

    String area;

    @Transient
    boolean submitted;


// --------------------------- CONSTRUCTORS ---------------------------

    public Question() {

        submitted = false;
    }

    protected Question(String text) {

        this.text = text;
        submitted = false;
    }

    public Question(String text, List<Answer> answers) {

        this.text = text;
        this.answers = new HashSet<Answer>(answers);
        submitted = false;
    }

// -------------------------- OTHER METHODS --------------------------

    @Transient
    @XmlElement(name = "discriminatorValue")
    public String getDiscriminatorValue() {

        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }

    public Answer getNthAnswer(int n) {

        return null; // TODO : fix or quiz will break !
    }

    public abstract double score();

// --------------------- GETTER / SETTER METHODS ---------------------

    @XmlElementWrapper(name = "answers")
    @XmlElement(name = "answer")
    public Set<Answer> getAnswers() {

        return answers;
    }

    public void setAnswers(Set<Answer> possibleAnswers) {

        this.answers = possibleAnswers;
    }

    public String getArea() {

        return area;
    }

    public void setArea(String area) {

        this.area = area;
    }

    @XmlTransient
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Level getLevel() {

        return level;
    }

    public void setLevel(Level level) {

        this.level = level;
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

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (area != null ? !area.equals(question.area) : question.area != null) return false;
        if (level != question.level) return false;
        if (text != null ? !text.equals(question.text) : question.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }


// -------------------------- ENUMERATIONS --------------------------

    public enum Level {
        ASSESSED, EXPERIENCED, SENIOR
    }
}
