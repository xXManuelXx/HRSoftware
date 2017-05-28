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
import com.hrsoftware.jpa.Lohnkonto;
import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Stammdaten;
import com.hrsoftware.jpacontroller.AbteilungFacade;
import com.hrsoftware.jpacontroller.MitarbeiterFacade;
import com.hrsoftware.jpacontroller.StammdatenFacade;
import com.hrsoftware.jsf.AbteilungController;
import com.hrsoftware.jsf.MitarbeiterController;
import com.hrsoftware.jsf.util.JsfUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
    private boolean lohnkontoVorhanden = false;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String datum;
    
    @Inject
    Gehaltsabrechnungsrechner gehaltsabrechnungsrechner;
    
    @Inject
    OptionalSalaryStatement optionalSalaryStatement;
    
    @Inject
    MonthView monthView;

    
    @Inject
    private com.hrsoftware.jpacontroller.LohnkontoFacade ejbFacadeLohnkonto;
    private Lohnkonto lohnkonto;
    
    
    
    @Inject
    private com.hrsoftware.jpacontroller.StammdatenFacade ejbFacadeBasedata;
    private Stammdaten selectedBasedata;

    @Inject
    private com.hrsoftware.jpacontroller.GehaltFacade ejbFacadeSalary;
    private Gehalt selectedSalary;
    
    @Inject
    private com.hrsoftware.jpacontroller.MitarbeiterFacade ejbFacadeEmployee;
    private List<Mitarbeiter> itemsEmployee = null;
    private Mitarbeiter selectedEmployee;
    //@Inject
    @Inject
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

    public Stammdaten getStammdaten() {
        return stammdaten;
    }

    public void setStammdaten(Stammdaten stammdaten) {
        this.stammdaten = stammdaten;
    }

    public Gehalt getSelectedSalary() {
        return selectedSalary;
    }

    public void setSelectedSalary(Gehalt selectedSalary) {
        this.selectedSalary = selectedSalary;
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
    
    private Lohnkonto loadLohnkonto(){
        lohnkonto = ejbFacadeLohnkonto.getLohnkontoDatenVonMonat(changeMonth(monthView.getMonth()));
        
        if(lohnkonto == null){
            System.out.println("Lohnkonto ist null");
            lohnkonto = new Lohnkonto();
            lohnkontoVorhanden = false;
        }else{
            System.out.println("Lohnkonto nicht null: " + lohnkonto.getEinmalbezuegeimbruttolohn());
            lohnkontoVorhanden = true;
        }
        return lohnkonto;
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
             
             gehaltsabrechnungsrechner.setMonth(monthView.getMonth());
             gehaltsabrechnungsrechner.setLohnkonto(loadLohnkonto());
             gehaltsabrechnungsrechner.setSumSteuerBruttoBisher(ejbFacadeLohnkonto.getSumSteuerBruttoBisher(selectedEmployee,changeMonth(monthView.getMonth())-1));
             gehaltsabrechnungsrechner.setRvPflichtigerBeitrag(ejbFacadeLohnkonto.getSumSVBruttoRV(selectedEmployee));
             gehaltsabrechnungsrechner.setKvPflichtigerBeitrag(ejbFacadeLohnkonto.getSumSVBruttoKV(selectedEmployee));
             gehaltsabrechnungsrechner.setEinmalbezuegeimbruttolohn(ejbFacadeLohnkonto.getEinmalbezuegeimbruttolohn(selectedEmployee));
             gehaltsabrechnungsrechner.calcAllValues();
             create();
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
    
     public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LohnkontoCreated"));
    }
     
     
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (lohnkonto != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    ejbFacadeLohnkonto.edit(lohnkonto);
                } else {
                    ejbFacadeLohnkonto.remove(lohnkonto);
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
    
    private int changeMonth(String month){
         switch(month){
            case "Januar":{
                return 1;
            }
            case "Februar":{
                return 2;
            }
            case "März":{
                return 3;
            }
            case "April":{
                return 4;
            }
            case "Mai":{
                return 5;
            }
            case "Juni":{
              return 6; 
            }
            case "Juli":{
               return 7;
            }
            case "August":{
                return 8;
            }
            case "September":{
                return 9;
            }
            case "Oktober":{
                return 10;
            }
            case "November":{
                return 11;
            }
            default:{
                return 12;
            }
            
        }
    }

    public String getDatum() {
        Date date = new Date();
        datum = sdf.format(date);

        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    
    
   
} 
