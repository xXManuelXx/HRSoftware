package com.hrsoftware.jsf;

import com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben;
import com.hrsoftware.jsf.util.JsfUtil;
import com.hrsoftware.jsf.util.JsfUtil.PersistAction;
import com.hrsoftware.jpaservice.GehaltsabrechnungvariableeingabenService;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("gehaltsabrechnungvariableeingabenController")
@SessionScoped
public class GehaltsabrechnungvariableeingabenController implements Serializable {

    @EJB
    private com.hrsoftware.jpaservice.GehaltsabrechnungvariableeingabenService ejbFacade;
    private List<Gehaltsabrechnungvariableeingaben> items = null;
    private Gehaltsabrechnungvariableeingaben selected;

    public GehaltsabrechnungvariableeingabenController() {
    }

    public Gehaltsabrechnungvariableeingaben getSelected() {
        return selected;
    }

    public void setSelected(Gehaltsabrechnungvariableeingaben selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GehaltsabrechnungvariableeingabenService getFacade() {
        return ejbFacade;
    }

    public Gehaltsabrechnungvariableeingaben prepareCreate() {
        selected = new Gehaltsabrechnungvariableeingaben();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GehaltsabrechnungvariableeingabenCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GehaltsabrechnungvariableeingabenUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GehaltsabrechnungvariableeingabenDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Gehaltsabrechnungvariableeingaben> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Gehaltsabrechnungvariableeingaben getGehaltsabrechnungvariableeingaben(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Gehaltsabrechnungvariableeingaben> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Gehaltsabrechnungvariableeingaben> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Gehaltsabrechnungvariableeingaben.class)
    public static class GehaltsabrechnungvariableeingabenControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GehaltsabrechnungvariableeingabenController controller = (GehaltsabrechnungvariableeingabenController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gehaltsabrechnungvariableeingabenController");
            return controller.getGehaltsabrechnungvariableeingaben(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Gehaltsabrechnungvariableeingaben) {
                Gehaltsabrechnungvariableeingaben o = (Gehaltsabrechnungvariableeingaben) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Gehaltsabrechnungvariableeingaben.class.getName()});
                return null;
            }
        }

    }

}
