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
import com.hrsoftware.jpa.Gehalt;
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
    private Stammdaten stammdaten;
    
    @Inject
    Gehaltsabrechnungsrechner gehaltsabrechnungsrechner;
    
    @Inject
    OptionalSalaryStatement optionalSalaryStatement;
    
    @Inject
    MonthView monthView;

    
    
    
    @EJB
    private com.hrsoftware.jpacontroller.StammdatenFacade ejbFacadeBasedata;
    private Stammdaten selectedBasedata;

    @EJB
    private com.hrsoftware.jpacontroller.GehaltFacade ejbFacadeSalary;
    private Gehalt selectedSalary;
    
    @EJB
    private com.hrsoftware.jpacontroller.MitarbeiterFacade ejbFacadeEmployee;
    private List<Mitarbeiter> itemsEmployee = null;
    private Mitarbeiter selectedEmployee;
    //@Inject
    @EJB
    private com.hrsoftware.jpacontroller.AbteilungFacade ejbFacadeDepartment;
    private List<Abteilung> itemsDepartment = null;
    private Abteilung selectedDepartment;

   
    
     public Gehaltsabrechnungsrechner getGehaltsabrechnungsrechner() {
        return gehaltsabrechnungsrechner;
    }
    
  public AbrechnungsController() {
  }

  public MonthView getMonthView() {
        return monthView;
    }

    public OptionalSalaryStatement getOptionalSalaryStatement() {
        return optionalSalaryStatement;
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
    
    public void createSalaryStatement(){
         System.out.println("onDepartmentChange wird ausgeführt: " + selectedEmployee.getNachname() + "  optional €  " + optionalSalaryStatement.getExpensesRefund() + " Object ausgabe: " +optionalSalaryStatement+ " Month: " + monthView.getMonth());
         Stammdaten stamm = ejbFacadeBasedata.getBaseDataFromEmployeeId(selectedEmployee.getId());
         if(stamm == null){
            System.out.println("Stammdaten sind null");
         }else{
            System.out.println("Stammdaten sind nicht  null: " + stamm.getId());
         }
         selectedBasedata = stamm;//ejbFacadeBasedata.getBaseDataFromEmployeeId(selectedBasedata.getMitarbeiterid());
         if(selectedBasedata != null && optionalSalaryStatement != null){
             selectedSalary = ejbFacadeSalary.findGehaltByID(selectedEmployee.getIdGehalt().getId());
             gehaltsabrechnungsrechner.setEmployeeSalaryYear(selectedSalary.getGehalt());
             gehaltsabrechnungsrechner.setEmployee(selectedEmployee);
             gehaltsabrechnungsrechner.setOptionalSalaryStatement(optionalSalaryStatement);
             gehaltsabrechnungsrechner.setStammdaten(selectedBasedata);
         }else{
             System.out.println("Stammdaten nicht geladen " );
         }
         /*if( selectedDepartment!=null){
             itemsEmployee.remove(0);
           // itemsEmployee = ejbFacadeEmployee.getEntityManager().createNamedQuery("Mitarbeiter.findByIdAbteilungId").setParameter("id",selectedDepartment.getId()).getResultList();     
         }else{
             itemsEmployee.remove(0);
             itemsEmployee.remove(1);
         }*/
    }
    
   
} 
