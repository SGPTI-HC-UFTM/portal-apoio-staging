package net.ebserh.hctm.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.util.AbstractEntity;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import java.util.logging.Level;
import java.util.logging.Logger;

@FacesConverter("dualListConverter")
public class DualListConverter implements Converter<AbstractEntity> {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Override
    public AbstractEntity getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            AbstractEntity entity = null;
            if (component instanceof PickList) {
                Object dualList = ((PickList) component).getValue();

                @SuppressWarnings("unchecked")
                DualListModel<AbstractEntity> dualListModel = (DualListModel<AbstractEntity>) dualList;
                for (AbstractEntity e : dualListModel.getSource()) {
                    if (String.valueOf(e).equals(value)) {
                        entity = e;
                        break;
                    }
                }

                if (entity == null) {
                    for (AbstractEntity e : dualListModel.getTarget()) {
                        if (String.valueOf(e).equals(value)) {
                            entity = e;
                            break;
                        }
                    }
                }
            }

            return entity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao carregar a lista de registros.");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AbstractEntity value) {
        return value.toString();
    }
    
}
