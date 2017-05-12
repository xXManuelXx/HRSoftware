/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mfehrenbach
 */
@Entity
@Table(name = "gehaltsabrechnungvariableeingaben")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findAll", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findById", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.id = :id")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByUrlaubsgeld", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.urlaubsgeld = :urlaubsgeld")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByPraemie", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.praemie = :praemie")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByDienstwagen", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.dienstwagen = :dienstwagen")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByDienstwagenWeg", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.dienstwagenWeg = :dienstwagenWeg")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByNachschicht", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.nachschicht = :nachschicht")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findBySonntage", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.sonntage = :sonntage")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByFeiertage", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.feiertage = :feiertage")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findBySonderverguetung", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.sonderverguetung = :sonderverguetung")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByAuslagenErstattungen", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.auslagenErstattungen = :auslagenErstattungen")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByFahrtgeld", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.fahrtgeld = :fahrtgeld")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByGehalt", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.gehalt = :gehalt")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findBySteuerklasse", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.steuerklasse = :steuerklasse")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByKinder", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.kinder = :kinder")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByKrankenversicherung", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.krankenversicherung = :krankenversicherung")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByZusatzbeitragProzent", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.zusatzbeitragProzent = :zusatzbeitragProzent")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByLohnsteuerfreibetrag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.lohnsteuerfreibetrag = :lohnsteuerfreibetrag")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByHinzurechnungsbetrag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.hinzurechnungsbetrag = :hinzurechnungsbetrag")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByEintrittsdatum", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.eintrittsdatum = :eintrittsdatum")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByUrlaubsanspruch", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.urlaubsanspruch = :urlaubsanspruch")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByAvPflichtig", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.avPflichtig = :avPflichtig")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByAltersfreibetrag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.altersfreibetrag = :altersfreibetrag")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByKrankheitstage", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.krankheitstage = :krankheitstage")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByUrlaubstageGenutzt", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.urlaubstageGenutzt = :urlaubstageGenutzt")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByLohnsteuer", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.lohnsteuer = :lohnsteuer")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByKirchensteuer", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.kirchensteuer = :kirchensteuer")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findBySolidarit\u00e4tszuschlag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.solidarit\u00e4tszuschlag = :solidarit\u00e4tszuschlag")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByKrankenkassenbeitrag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.krankenkassenbeitrag = :krankenkassenbeitrag")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByZusatzbeitragGeld", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.zusatzbeitragGeld = :zusatzbeitragGeld")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByArbeitslosenversicherung", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.arbeitslosenversicherung = :arbeitslosenversicherung")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByRentenversicherung", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.rentenversicherung = :rentenversicherung")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByPflegeversicherung", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.pflegeversicherung = :pflegeversicherung")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByAgzuschussbaV", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.agzuschussbaV = :agzuschussbaV")
    , @NamedQuery(name = "Gehaltsabrechnungvariableeingaben.findByAuszahlungsbetrag", query = "SELECT g FROM Gehaltsabrechnungvariableeingaben g WHERE g.auszahlungsbetrag = :auszahlungsbetrag")})
public class Gehaltsabrechnungvariableeingaben implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "urlaubsgeld")
    private double urlaubsgeld;
    @Basic(optional = false)
    @NotNull
    @Column(name = "praemie")
    private double praemie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dienstwagen")
    private double dienstwagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dienstwagen_weg")
    private double dienstwagenWeg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nachschicht")
    private double nachschicht;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sonntage")
    private double sonntage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feiertage")
    private double feiertage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sonderverguetung")
    private double sonderverguetung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auslagen_erstattungen")
    private double auslagenErstattungen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fahrtgeld")
    private double fahrtgeld;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gehalt")
    private double gehalt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steuerklasse")
    private int steuerklasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kinder")
    private int kinder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "krankenversicherung")
    private double krankenversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zusatzbeitrag_prozent")
    private double zusatzbeitragProzent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lohnsteuerfreibetrag")
    private double lohnsteuerfreibetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hinzurechnungsbetrag")
    private double hinzurechnungsbetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eintrittsdatum")
    @Temporal(TemporalType.DATE)
    private Date eintrittsdatum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "urlaubsanspruch")
    private int urlaubsanspruch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "av_pflichtig")
    private int avPflichtig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "altersfreibetrag")
    private double altersfreibetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "krankheitstage")
    private int krankheitstage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "urlaubstage_genutzt")
    private int urlaubstageGenutzt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lohnsteuer")
    private double lohnsteuer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kirchensteuer")
    private double kirchensteuer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solidarit\u00e4tszuschlag")
    private double solidaritätszuschlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "krankenkassenbeitrag")
    private double krankenkassenbeitrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zusatzbeitrag_geld")
    private double zusatzbeitragGeld;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arbeitslosenversicherung")
    private double arbeitslosenversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentenversicherung")
    private double rentenversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pflegeversicherung")
    private double pflegeversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ag_zuschuss_baV")
    private double agzuschussbaV;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auszahlungsbetrag")
    private double auszahlungsbetrag;
    @JoinColumn(name = "ma_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Mitarbeiter maId;

    public Gehaltsabrechnungvariableeingaben() {
    }

    public Gehaltsabrechnungvariableeingaben(Integer id) {
        this.id = id;
    }

    public Gehaltsabrechnungvariableeingaben(Integer id, double urlaubsgeld, double praemie, double dienstwagen, double dienstwagenWeg, double nachschicht, double sonntage, double feiertage, double sonderverguetung, double auslagenErstattungen, double fahrtgeld, double gehalt, int steuerklasse, int kinder, double krankenversicherung, double zusatzbeitragProzent, double lohnsteuerfreibetrag, double hinzurechnungsbetrag, Date eintrittsdatum, int urlaubsanspruch, int avPflichtig, double altersfreibetrag, int krankheitstage, int urlaubstageGenutzt, double lohnsteuer, double kirchensteuer, double solidaritätszuschlag, double krankenkassenbeitrag, double zusatzbeitragGeld, double arbeitslosenversicherung, double rentenversicherung, double pflegeversicherung, double agzuschussbaV, double auszahlungsbetrag) {
        this.id = id;
        this.urlaubsgeld = urlaubsgeld;
        this.praemie = praemie;
        this.dienstwagen = dienstwagen;
        this.dienstwagenWeg = dienstwagenWeg;
        this.nachschicht = nachschicht;
        this.sonntage = sonntage;
        this.feiertage = feiertage;
        this.sonderverguetung = sonderverguetung;
        this.auslagenErstattungen = auslagenErstattungen;
        this.fahrtgeld = fahrtgeld;
        this.gehalt = gehalt;
        this.steuerklasse = steuerklasse;
        this.kinder = kinder;
        this.krankenversicherung = krankenversicherung;
        this.zusatzbeitragProzent = zusatzbeitragProzent;
        this.lohnsteuerfreibetrag = lohnsteuerfreibetrag;
        this.hinzurechnungsbetrag = hinzurechnungsbetrag;
        this.eintrittsdatum = eintrittsdatum;
        this.urlaubsanspruch = urlaubsanspruch;
        this.avPflichtig = avPflichtig;
        this.altersfreibetrag = altersfreibetrag;
        this.krankheitstage = krankheitstage;
        this.urlaubstageGenutzt = urlaubstageGenutzt;
        this.lohnsteuer = lohnsteuer;
        this.kirchensteuer = kirchensteuer;
        this.solidaritätszuschlag = solidaritätszuschlag;
        this.krankenkassenbeitrag = krankenkassenbeitrag;
        this.zusatzbeitragGeld = zusatzbeitragGeld;
        this.arbeitslosenversicherung = arbeitslosenversicherung;
        this.rentenversicherung = rentenversicherung;
        this.pflegeversicherung = pflegeversicherung;
        this.agzuschussbaV = agzuschussbaV;
        this.auszahlungsbetrag = auszahlungsbetrag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getUrlaubsgeld() {
        return urlaubsgeld;
    }

    public void setUrlaubsgeld(double urlaubsgeld) {
        this.urlaubsgeld = urlaubsgeld;
    }

    public double getPraemie() {
        return praemie;
    }

    public void setPraemie(double praemie) {
        this.praemie = praemie;
    }

    public double getDienstwagen() {
        return dienstwagen;
    }

    public void setDienstwagen(double dienstwagen) {
        this.dienstwagen = dienstwagen;
    }

    public double getDienstwagenWeg() {
        return dienstwagenWeg;
    }

    public void setDienstwagenWeg(double dienstwagenWeg) {
        this.dienstwagenWeg = dienstwagenWeg;
    }

    public double getNachschicht() {
        return nachschicht;
    }

    public void setNachschicht(double nachschicht) {
        this.nachschicht = nachschicht;
    }

    public double getSonntage() {
        return sonntage;
    }

    public void setSonntage(double sonntage) {
        this.sonntage = sonntage;
    }

    public double getFeiertage() {
        return feiertage;
    }

    public void setFeiertage(double feiertage) {
        this.feiertage = feiertage;
    }

    public double getSonderverguetung() {
        return sonderverguetung;
    }

    public void setSonderverguetung(double sonderverguetung) {
        this.sonderverguetung = sonderverguetung;
    }

    public double getAuslagenErstattungen() {
        return auslagenErstattungen;
    }

    public void setAuslagenErstattungen(double auslagenErstattungen) {
        this.auslagenErstattungen = auslagenErstattungen;
    }

    public double getFahrtgeld() {
        return fahrtgeld;
    }

    public void setFahrtgeld(double fahrtgeld) {
        this.fahrtgeld = fahrtgeld;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public int getSteuerklasse() {
        return steuerklasse;
    }

    public void setSteuerklasse(int steuerklasse) {
        this.steuerklasse = steuerklasse;
    }

    public int getKinder() {
        return kinder;
    }

    public void setKinder(int kinder) {
        this.kinder = kinder;
    }

    public double getKrankenversicherung() {
        return krankenversicherung;
    }

    public void setKrankenversicherung(double krankenversicherung) {
        this.krankenversicherung = krankenversicherung;
    }

    public double getZusatzbeitragProzent() {
        return zusatzbeitragProzent;
    }

    public void setZusatzbeitragProzent(double zusatzbeitragProzent) {
        this.zusatzbeitragProzent = zusatzbeitragProzent;
    }

    public double getLohnsteuerfreibetrag() {
        return lohnsteuerfreibetrag;
    }

    public void setLohnsteuerfreibetrag(double lohnsteuerfreibetrag) {
        this.lohnsteuerfreibetrag = lohnsteuerfreibetrag;
    }

    public double getHinzurechnungsbetrag() {
        return hinzurechnungsbetrag;
    }

    public void setHinzurechnungsbetrag(double hinzurechnungsbetrag) {
        this.hinzurechnungsbetrag = hinzurechnungsbetrag;
    }

    public Date getEintrittsdatum() {
        return eintrittsdatum;
    }

    public void setEintrittsdatum(Date eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }

    public int getUrlaubsanspruch() {
        return urlaubsanspruch;
    }

    public void setUrlaubsanspruch(int urlaubsanspruch) {
        this.urlaubsanspruch = urlaubsanspruch;
    }

    public int getAvPflichtig() {
        return avPflichtig;
    }

    public void setAvPflichtig(int avPflichtig) {
        this.avPflichtig = avPflichtig;
    }

    public double getAltersfreibetrag() {
        return altersfreibetrag;
    }

    public void setAltersfreibetrag(double altersfreibetrag) {
        this.altersfreibetrag = altersfreibetrag;
    }

    public int getKrankheitstage() {
        return krankheitstage;
    }

    public void setKrankheitstage(int krankheitstage) {
        this.krankheitstage = krankheitstage;
    }

    public int getUrlaubstageGenutzt() {
        return urlaubstageGenutzt;
    }

    public void setUrlaubstageGenutzt(int urlaubstageGenutzt) {
        this.urlaubstageGenutzt = urlaubstageGenutzt;
    }

    public double getLohnsteuer() {
        return lohnsteuer;
    }

    public void setLohnsteuer(double lohnsteuer) {
        this.lohnsteuer = lohnsteuer;
    }

    public double getKirchensteuer() {
        return kirchensteuer;
    }

    public void setKirchensteuer(double kirchensteuer) {
        this.kirchensteuer = kirchensteuer;
    }

    public double getSolidaritätszuschlag() {
        return solidaritätszuschlag;
    }

    public void setSolidaritätszuschlag(double solidaritätszuschlag) {
        this.solidaritätszuschlag = solidaritätszuschlag;
    }

    public double getKrankenkassenbeitrag() {
        return krankenkassenbeitrag;
    }

    public void setKrankenkassenbeitrag(double krankenkassenbeitrag) {
        this.krankenkassenbeitrag = krankenkassenbeitrag;
    }

    public double getZusatzbeitragGeld() {
        return zusatzbeitragGeld;
    }

    public void setZusatzbeitragGeld(double zusatzbeitragGeld) {
        this.zusatzbeitragGeld = zusatzbeitragGeld;
    }

    public double getArbeitslosenversicherung() {
        return arbeitslosenversicherung;
    }

    public void setArbeitslosenversicherung(double arbeitslosenversicherung) {
        this.arbeitslosenversicherung = arbeitslosenversicherung;
    }

    public double getRentenversicherung() {
        return rentenversicherung;
    }

    public void setRentenversicherung(double rentenversicherung) {
        this.rentenversicherung = rentenversicherung;
    }

    public double getPflegeversicherung() {
        return pflegeversicherung;
    }

    public void setPflegeversicherung(double pflegeversicherung) {
        this.pflegeversicherung = pflegeversicherung;
    }

    public double getAgzuschussbaV() {
        return agzuschussbaV;
    }

    public void setAgzuschussbaV(double agzuschussbaV) {
        this.agzuschussbaV = agzuschussbaV;
    }

    public double getAuszahlungsbetrag() {
        return auszahlungsbetrag;
    }

    public void setAuszahlungsbetrag(double auszahlungsbetrag) {
        this.auszahlungsbetrag = auszahlungsbetrag;
    }

    public Mitarbeiter getMaId() {
        return maId;
    }

    public void setMaId(Mitarbeiter maId) {
        this.maId = maId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gehaltsabrechnungvariableeingaben)) {
            return false;
        }
        Gehaltsabrechnungvariableeingaben other = (Gehaltsabrechnungvariableeingaben) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben[ id=" + id + " ]";
    }
    
}
