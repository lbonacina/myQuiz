package myQuiz.util.converters;

import myQuiz.model.quiz.Quiz;
import org.omnifaces.converter.SelectItemsConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 * Created with IntelliJ IDEA.
 * User: eluibon
 * Date: 11/02/13
 * Time: 15.53
 */
@FacesConverter("quizSelectItemsConverter")
public class QuizSelectItemsConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Long id = (value instanceof Quiz) ? ((Quiz) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
}
