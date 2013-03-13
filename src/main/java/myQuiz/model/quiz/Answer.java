package myQuiz.model.quiz;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 06/03/13
 * Time: 10.57
 */
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_question", referencedColumnName = "id", nullable = true)
    Question question;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_answer", referencedColumnName = "id", nullable = true)
    PossibleAnswer answer;

    public Answer() {
    }

    public Answer(Question question, PossibleAnswer answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public PossibleAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(PossibleAnswer answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer1 = (Answer) o;

        if (answer != null ? !answer.equals(answer1.answer) : answer1.answer != null) return false;
        if (question != null ? !question.equals(answer1.question) : answer1.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
