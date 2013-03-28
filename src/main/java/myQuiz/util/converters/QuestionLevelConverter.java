package myQuiz.util.converters;

import myQuiz.model.quiz.Question;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 28/03/13
 * Time: 14.12
 */
@FacesConverter(value = "questionLevelConverter")
public class QuestionLevelConverter extends EnumConverter {

    public QuestionLevelConverter() {

        super(Question.Level.class);
    }
}
