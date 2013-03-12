package myApp.model.quiz;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@DiscriminatorColumn(name="type")
public abstract class Question {
// ------------------------------ FIELDS ------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @NotNull
    @Size(min = 10, max = 4000)
    String text;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="question_id",nullable = false)
    List<PossibleAnswer> possibleAnswers ;

// --------------------------- CONSTRUCTORS ---------------------------

    protected Question() {
    }

    protected Question(String text) {
        this.text = text;
    }

    public Question(String text, List<PossibleAnswer> possibleAnswers) {
        this.text = text;
        this.possibleAnswers = possibleAnswers ;
    }

    public PossibleAnswer getNthPossibleAnswer(int n) {

        return possibleAnswers.get(n-1) ;
    }

    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : val.value();
    }

    public abstract double score(List<PossibleAnswer> userAnswers) ;

    // --------------------- GETTER / SETTER METHODS ---------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
