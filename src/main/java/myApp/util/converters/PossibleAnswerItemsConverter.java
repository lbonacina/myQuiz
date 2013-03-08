package myApp.util.converters;

import myApp.model.quiz.PossibleAnswer;
import myApp.model.user.User;
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
@FacesConverter("possibleAnswerItemsConverter")
public class PossibleAnswerItemsConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Long id = (value instanceof PossibleAnswer) ? ((PossibleAnswer) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
}
