package myApp.model.quiz;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class Quiz {
// ------------------------------ FIELDS ------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 4000)
    String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_quiz")
    List<Question> questions;

// --------------------------- CONSTRUCTORS ---------------------------

    public Quiz() {
    }

    public Quiz(String name) {
        this.name = name;
        questions = new ArrayList<Question>();
    }

// -------------------------- OTHER METHODS --------------------------

    public void addQuestion(Question question) {
        questions.add(question);
    }

    // 1-based
    public Question getNthQuestion(int n) {
        return questions.get(n - 1);
    }

    public int getNumberOfQuestions() {
        return questions.size();
    }

// --------------------- GETTER / SETTER METHODS ---------------------

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

    protected List<Question> getQuestions() {
        return questions;
    }

    protected void setQuestions(List<Question> questions) {
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
