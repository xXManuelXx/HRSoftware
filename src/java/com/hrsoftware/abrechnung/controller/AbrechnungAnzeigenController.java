/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.abrechnung.controller;

import com.hrsoftware.abrechnung.model.MonthView;
import com.hrsoftware.abrechnung.model.OptionalSalaryInput;
import com.hrsoftware.abrechnung.model.Gehaltsabrechnungsrechner;
import com.hrsoftware.jpa.Abteilung;
import com.hrsoftware.jpa.Gehalt;
import com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben;
import com.hrsoftware.jpa.Lohnkonto;
import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Stammdaten;
import com.hrsoftware.jpaservice.AbteilungFacade;
import com.hrsoftware.jpaservice.MitarbeiterFacade;
import com.hrsoftware.jpaservice.StammdatenFacade;
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
/**
 *
 * @author mfehrenbach
 */
@Named("abrechnungAnzeigenController")
@ViewScoped
public class AbrechnungAnzeigenController implements Serializable {

    private boolean all = true;
    private Stammdaten baseData;
    private boolean payrollAccountExisting = false;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String date = "";
    private String birthdate = "";
    private double calcSalaryMonth = 0.0d;
    private String message;

    @Inject
    MonthView monthView;

    @Inject
    private com.hrsoftware.jpaservice.LohnkontoFacade ejbFacadePayrollAccount;
    private Lohnkonto payrollAccount;

    @Inject
    private com.hrsoftware.jpaservice.StammdatenFacade ejbFacadeBasedata;
    private Stammdaten selectedBasedata;

    @Inject
    private com.hrsoftware.jpaservice.MitarbeiterFacade ejbFacadeEmployee;
    private List<Mitarbeiter> itemsEmployee = null;
    private Mitarbeiter selectedEmployee;

    public MonthView getMonthView() {
        return monthView;
    }

    public Mitarbeiter getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Mitarbeiter selected) {
        this.selectedEmployee = selected;
    }

    public List<Mitarbeiter> getItemsEmployee() {
        if (itemsEmployee == null && all) {
            all = false;
            itemsEmployee = getFacadeEmployee().findAll();
        }
        return itemsEmployee;
    }

    public Stammdaten getSelectedBaseData() {
        return selectedBasedata;
    }

    public void setSelectedBaseData(Stammdaten baseData) {
        this.selectedBasedata = baseData;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MitarbeiterFacade getFacadeEmployee() {
        return ejbFacadeEmployee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Lohnkonto getPayrollAccount() {
        return payrollAccount;
    }

    public void setPayrollAccount(Lohnkonto payrollAccount) {
        this.payrollAccount = payrollAccount;
    }

    private void loadpayrollAccount() {
        payrollAccount = ejbFacadePayrollAccount.getLohnkontoDatenVonMonatUndMaId(changeMonth(monthView.getMonth()), selectedEmployee);

    }

    public void showSalaryStatement() {
        if (selectedEmployee != null) {
            Stammdaten stamm = ejbFacadeBasedata.getBaseDataFromEmployeeId(selectedEmployee.getId());
            selectedBasedata = stamm;
            loadpayrollAccount();
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Mitarbeiter auswählen","") );
        }
    }

    private int changeMonth(String month) {
        switch (month) {
            case "Januar": {
                return 1;
            }
            case "Februar": {
                return 2;
            }
            case "März": {
                return 3;
            }
            case "April": {
                return 4;
            }
            case "Mai": {
                return 5;
            }
            case "Juni": {
                return 6;
            }
            case "Juli": {
                return 7;
            }
            case "August": {
                return 8;
            }
            case "September": {
                return 9;
            }
            case "Oktober": {
                return 10;
            }
            case "November": {
                return 11;
            }
            default: {
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
        if (selectedEmployee != null) {
            return format.format(selectedEmployee.getGeburtsdatum());
        } else {
            return birthdate;
        }
    }

    public void setdate(String date) {
        this.date = date;
    }
}
