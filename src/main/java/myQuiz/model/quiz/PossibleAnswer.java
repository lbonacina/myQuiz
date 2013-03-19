package myQuiz.model.quiz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.57
 */
@Entity
@Table(name = "possible_answer")
public class PossibleAnswer implements Serializable {
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

// --------------------------- CONSTRUCTORS ---------------------------

    public PossibleAnswer() {
    }

    public PossibleAnswer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

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

    public boolean isCorrect() {
        return correct;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PossibleAnswer that = (PossibleAnswer) o;

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
