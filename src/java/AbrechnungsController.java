/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfehrenbach
 */
import com.hrsoftware.jpa.Abteilung;
import com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben;
import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Stammdaten;
import com.hrsoftware.jpacontroller.AbteilungFacade;
import com.hrsoftware.jpacontroller.MitarbeiterFacade;
import com.hrsoftware.jpacontroller.StammdatenFacade;
import com.hrsoftware.jsf.AbteilungController;
import com.hrsoftware.jsf.MitarbeiterController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
 
@Named("abrechnungsController")
@SessionScoped
public class AbrechnungsController  implements Serializable {
    private boolean all = true; 
    
    @EJB
    private com.hrsoftware.jpacontroller.MitarbeiterFacade ejbFacadeEmployee;
    private List<Mitarbeiter> itemsEmployee = null;
    private Mitarbeiter selectedEmployee;
    //@Inject
    @EJB
    private com.hrsoftware.jpacontroller.AbteilungFacade ejbFacadeDepartment;
    private List<Abteilung> itemsDepartment = null;
    private Abteilung selectedDepartment;

    
    
    
  public AbrechnungsController() {
  }

    
    public Mitarbeiter getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Mitarbeiter selected) {
        this.selectedEmployee = selected;
    }
    
     public List<Mitarbeiter> getItemsEmployee() {
         System.out.println("employee in der methode wird ausgeführt");
        if (itemsEmployee == null && all) {
            System.out.println("Alle Employeesholen in der if");
            all = false;
            itemsEmployee = getFacadeEmployee().findAll();
        }
        return itemsEmployee;
    }
     
     public List<Abteilung> getItemsDepartment() {
        if (itemsDepartment == null) {
            itemsDepartment = getFacadeDepartment().findAll();
            
        }
        return itemsDepartment;
    }
    
    public Abteilung getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Abteilung selected) {
        this.selectedDepartment = selected;
    }
    
    
     protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MitarbeiterFacade getFacadeEmployee() {
        return ejbFacadeEmployee;
    }
    
    private AbteilungFacade getFacadeDepartment() {
        return ejbFacadeDepartment;
    }
    
    public void onDepartmentChange(){
        System.out.println("onDepartmentChange wird ausgeführt");
         /*if( selectedDepartment!=null){
             itemsEmployee.remove(0);
           // itemsEmployee = ejbFacadeEmployee.getEntityManager().createNamedQuery("Mitarbeiter.findByIdAbteilungId").setParameter("id",selectedDepartment.getId()).getResultList();     
         }else{
             itemsEmployee.remove(0);
             itemsEmployee.remove(1);
         }*/
    }
    
    @FacesConverter(forClass = Abteilung.class)
    public static class AbrechnungsControllerConverterDepartment implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AbteilungController controller = (AbteilungController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "abrechnungsController");
            return controller.getAbteilung(getKey(value));
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
            if (object instanceof Abteilung) {
                Abteilung o = (Abteilung) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Abteilung.class.getName()});
                return null;
            }
        }

    }
     
     
    @FacesConverter(forClass = Mitarbeiter.class)
    public static class AbrechnungsControllerConverterEmployee implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MitarbeiterController controller = (MitarbeiterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "abrechnungsController");
            return controller.getMitarbeiter(getKey(value));
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
            if (object instanceof Mitarbeiter) {
                Mitarbeiter o = (Mitarbeiter) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mitarbeiter.class.getName()});
                return null;
            }
        }

    }
} 
