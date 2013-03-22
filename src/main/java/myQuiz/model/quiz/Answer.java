package myQuiz.model.quiz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.57
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@XmlType(propOrder = {"text", "correct", "checked"})
public class Answer implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 2217728808580244207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Size(min = 10, max = 4000)
    String text;

    @NotNull
    @Column
    boolean correct;

    @Transient
    boolean checked;

// --------------------------- CONSTRUCTORS ---------------------------

    public Answer() {
    }

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
        this.checked = false;
    }

    public void mark() {

        checked = true;
    }

    public void unmark() {

        checked = false;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement
    public boolean isCorrect() {
        return correct;
    }

    @XmlElement
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    // ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer that = (Answer) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PossibleAnswer{" +
                "correct=" + correct +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
