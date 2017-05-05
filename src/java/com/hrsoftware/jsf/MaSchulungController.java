package com.hrsoftware.jsf;

import com.hrsoftware.jpa.MaSchulung;
import com.hrsoftware.jsf.util.JsfUtil;
import com.hrsoftware.jsf.util.JsfUtil.PersistAction;
import com.hrsoftware.jpacontroller.MaSchulungFacade;

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

@Named("maSchulungController")
@SessionScoped
public class MaSchulungController implements Serializable {

    @EJB
    private com.hrsoftware.jpacontroller.MaSchulungFacade ejbFacade;
    private List<MaSchulung> items = null;
    private MaSchulung selected;

    public MaSchulungController() {
    }

    public MaSchulung getSelected() {
        return selected;
    }

    public void setSelected(MaSchulung selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setMaSchulungPK(new com.hrsoftware.jpa.MaSchulungPK());
    }

    private MaSchulungFacade getFacade() {
        return ejbFacade;
    }

    public MaSchulung prepareCreate() {
        selected = new MaSchulung();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaSchulungCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaSchulungUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaSchulungDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaSchulung> getItems() {
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

    public MaSchulung getMaSchulung(com.hrsoftware.jpa.MaSchulungPK id) {
        return getFacade().find(id);
    }

    public List<MaSchulung> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaSchulung> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MaSchulung.class)
    public static class MaSchulungControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaSchulungController controller = (MaSchulungController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maSchulungController");
            return controller.getMaSchulung(getKey(value));
        }

        com.hrsoftware.jpa.MaSchulungPK getKey(String value) {
            com.hrsoftware.jpa.MaSchulungPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.hrsoftware.jpa.MaSchulungPK();
            key.setSchulungId(Integer.parseInt(values[0]));
            key.setMaId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.hrsoftware.jpa.MaSchulungPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getSchulungId());
            sb.append(SEPARATOR);
            sb.append(value.getMaId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaSchulung) {
                MaSchulung o = (MaSchulung) object;
                return getStringKey(o.getMaSchulungPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaSchulung.class.getName()});
                return null;
            }
        }

    }

}
