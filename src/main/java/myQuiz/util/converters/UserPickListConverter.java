package myQuiz.util.converters;

import myQuiz.model.user.User;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "UserPickListConverter")
public class UserPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent component, String id) {

        Long idAsLong = Long.valueOf(id);

        if (component instanceof PickList) {
            Object dualList = ((PickList) component).getValue();
            DualListModel dl = (DualListModel) dualList;

            for (Object obj : dl.getSource()) {
                User user = (User) obj;
                if (user.getId().equals(idAsLong))
                    return user;
            }
            for (Object obj : dl.getTarget()) {
                User user = (User) obj;
                if (user.getId().equals(idAsLong))
                    return user;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent component, Object obj) {
        User user = (User) obj;
        return String.valueOf(user.getId());
    }
}