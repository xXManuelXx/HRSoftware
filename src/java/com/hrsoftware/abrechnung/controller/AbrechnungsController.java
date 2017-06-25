package com.hrsoftware.abrechnung.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfehrenbach
 */
import com.hrsoftware.abrechnung.model.MonthView;
import com.hrsoftware.abrechnung.model.OptionalSalaryInput;
import com.hrsoftware.abrechnung.model.Gehaltsabrechnungsrechner;
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
    private Stammdaten baseData;
    private boolean payrollAccountExisting = false;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String date = "";
    private String birthdate ="";
    private double calcSalaryMonth = 0.0d;
    private String message;
    

    
    @Inject
    Gehaltsabrechnungsrechner payrollCalculator;
    
    @Inject
    OptionalSalaryInput optionalSalaryInput;
    
    @Inject
    MonthView monthView;

    
    @Inject
    private com.hrsoftware.jpacontroller.LohnkontoFacade ejbFacadePayrollAccount;
    private Lohnkonto payrollAccount;
    
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
   
    @Inject
    private com.hrsoftware.jpacontroller.AbteilungFacade ejbFacadeDepartment;
    private List<Abteilung> itemsDepartment = null;
    private Abteilung selectedDepartment;

     
     public Gehaltsabrechnungsrechner getpayrollCalculator() {
        return payrollCalculator;
    }
    
  public AbrechnungsController() {
  }

  public MonthView getMonthView() {
        return monthView;
    }

    public OptionalSalaryInput getOptionalSalaryInput() {
        return optionalSalaryInput;
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

    public Stammdaten getSelectedBaseData() {
        return selectedBasedata;
    }

    public void setSelectedBaseData(Stammdaten baseData) {
        this.selectedBasedata = baseData;
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
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    private Lohnkonto loadpayrollAccount(){

        payrollAccount = ejbFacadePayrollAccount.getLohnkontoDatenVonMonat(changeMonth(monthView.getMonth()));
        
        if(payrollAccount == null){
            System.out.println("payrollAccount ist null");
            payrollAccount = new Lohnkonto();
            payrollAccountExisting = false;
        }else{
            System.out.println("payrollAccount nicht null: " + payrollAccount.getEinmalbezuegeimbruttolohn());
            payrollAccountExisting = true;
        }
        return payrollAccount;
    }
    
    public void createSalaryStatement(){
         System.out.println("onDepartmentChange wird ausgeführt: " + selectedEmployee.getNachname() + "  optional €  " + optionalSalaryInput.getExpensesRefund() + " Object ausgabe: " +optionalSalaryInput+ " Month: " + monthView.getMonth());
         Stammdaten stamm = ejbFacadeBasedata.getBaseDataFromEmployeeId(selectedEmployee.getId());
         if(stamm == null){
            System.out.println("baseData sind null");
         }else{
            System.out.println("baseData sind nicht  null: " + stamm.getId());
         }
         selectedBasedata = stamm;//ejbFacadeBasedata.getBaseDataFromEmployeeId(selectedBasedata.getMitarbeiterid());
         if(selectedBasedata != null && optionalSalaryInput != null){
             selectedSalary = ejbFacadeSalary.findGehaltByID(selectedEmployee.getIdGehalt().getId());
             payrollCalculator.setEmployeeSalaryYear(selectedSalary.getGehalt());
             payrollCalculator.setEmployee(selectedEmployee);
             payrollCalculator.setOptionalSalaryInput(optionalSalaryInput);
             payrollCalculator.setbaseData(selectedBasedata);
             
             payrollCalculator.setMonth(monthView.getMonth());
             payrollCalculator.setPayrollAccount(loadpayrollAccount());
             payrollCalculator.setSumTaxGrossTillNow(ejbFacadePayrollAccount.getSumSteuerBruttoBisher(selectedEmployee,changeMonth(monthView.getMonth())-1));
             payrollCalculator.setRvPflichtigerBeitrag(ejbFacadePayrollAccount.getSumSVBruttoRV(selectedEmployee));
             payrollCalculator.setKvPflichtigerBeitrag(ejbFacadePayrollAccount.getSumSVBruttoKV(selectedEmployee));
             payrollCalculator.setOnetimeEarningsInTheGrossEarning(ejbFacadePayrollAccount.getEinmalbezuegeimbruttolohn(selectedEmployee));
             payrollCalculator.calcAllValues();
             create();
         }else{
             System.out.println("baseData nicht geladen " );
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
        persist(JsfUtil.PersistAction.CREATE);
    }
     
     
    private void persist(JsfUtil.PersistAction persistAction) {
        if (payrollAccount != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == JsfUtil.PersistAction.CREATE && !payrollAccountExisting) {
                    ejbFacadePayrollAccount.create(payrollAccount);
                }else if (persistAction == JsfUtil.PersistAction.CREATE && payrollAccountExisting) {
                    ejbFacadePayrollAccount.edit(payrollAccount);
                }
               // JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                    System.out.println("msg: " + msg);
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                    
                } else {
                    System.out.println("persistenceErrorOccured: " + msg);
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                System.out.println("ex: " + ex.getMessage());
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

    public String getdate() {
        Date newdate = new Date();
        date = sdf.format(newdate);

        return date;
    }
    
    public String getBirthdate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        
       // String date = sdf.format(newdate);
        if(selectedEmployee != null){
            return format.format(selectedEmployee.getGeburtsdatum());
        }else{
            return birthdate;
        }
    }

    public void setdate(String date) {
        this.date = date;
    }

    public double getCalcSalaryMonth() {
        if(selectedSalary != null)
            calcSalaryMonth = Math.floor(((selectedSalary.getGehalt()/12)*100.0)/100.0);
        return calcSalaryMonth;
    }

    public void setCalcSalaryMonth(double salaryMonth) {
        this.calcSalaryMonth = salaryMonth;
    }
    
    
    
   
} 
