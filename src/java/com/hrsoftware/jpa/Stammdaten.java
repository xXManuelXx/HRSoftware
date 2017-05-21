/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mfehrenbach
 */
@Entity
@Table(name = "stammdaten")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stammdaten.findAll", query = "SELECT s FROM Stammdaten s")
    , @NamedQuery(name = "Stammdaten.findById", query = "SELECT s FROM Stammdaten s WHERE s.id = :id")
    , @NamedQuery(name = "Stammdaten.findBySteuerklasse", query = "SELECT s FROM Stammdaten s WHERE s.steuerklasse = :steuerklasse")
    , @NamedQuery(name = "Stammdaten.findByEhegattenfaktor", query = "SELECT s FROM Stammdaten s WHERE s.ehegattenfaktor = :ehegattenfaktor")
    , @NamedQuery(name = "Stammdaten.findByRentenversichert", query = "SELECT s FROM Stammdaten s WHERE s.rentenversichert = :rentenversichert")
    , @NamedQuery(name = "Stammdaten.findByArbeitslosenversicherungspflichtig", query = "SELECT s FROM Stammdaten s WHERE s.arbeitslosenversicherungspflichtig = :arbeitslosenversicherungspflichtig")
    , @NamedQuery(name = "Stammdaten.findByKinderfreibetrag", query = "SELECT s FROM Stammdaten s WHERE s.kinderfreibetrag = :kinderfreibetrag")
    , @NamedQuery(name = "Stammdaten.findByKrankenversicherung", query = "SELECT s FROM Stammdaten s WHERE s.krankenversicherung = :krankenversicherung")
    , @NamedQuery(name = "Stammdaten.findByKvzuschlag", query = "SELECT s FROM Stammdaten s WHERE s.kvzuschlag = :kvzuschlag")
    , @NamedQuery(name = "Stammdaten.findByArbeitgeberzuschussKvPv", query = "SELECT s FROM Stammdaten s WHERE s.arbeitgeberzuschussKvPv = :arbeitgeberzuschussKvPv")
    , @NamedQuery(name = "Stammdaten.findByKinderlos", query = "SELECT s FROM Stammdaten s WHERE s.kinderlos = :kinderlos")
    , @NamedQuery(name = "Stammdaten.findByStelleImOsten", query = "SELECT s FROM Stammdaten s WHERE s.stelleImOsten = :stelleImOsten")
    , @NamedQuery(name = "Stammdaten.findByStelleInSachsen", query = "SELECT s FROM Stammdaten s WHERE s.stelleInSachsen = :stelleInSachsen")
    , @NamedQuery(name = "Stammdaten.findByKirchensteuer", query = "SELECT s FROM Stammdaten s WHERE s.kirchensteuer = :kirchensteuer")
    , @NamedQuery(name = "Stammdaten.findByLohnsteuerfreibetrag", query = "SELECT s FROM Stammdaten s WHERE s.lohnsteuerfreibetrag = :lohnsteuerfreibetrag")
    , @NamedQuery(name = "Stammdaten.findByHinzurechnungsbetrag", query = "SELECT s FROM Stammdaten s WHERE s.hinzurechnungsbetrag = :hinzurechnungsbetrag")
    , @NamedQuery(name = "Stammdaten.findByMinijobGleizonenberechnung", query = "SELECT s FROM Stammdaten s WHERE s.minijobGleizonenberechnung = :minijobGleizonenberechnung")
    , @NamedQuery(name = "Stammdaten.findByRentenanwartschaft", query = "SELECT s FROM Stammdaten s WHERE s.rentenanwartschaft = :rentenanwartschaft")
    , @NamedQuery(name = "Stammdaten.findByMitarbeiterid", query = "SELECT s FROM Stammdaten s WHERE s.mitarbeiterid = :mitarbeiterid")})
public class Stammdaten implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steuerklasse")
    private int steuerklasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ehegattenfaktor")
    private int ehegattenfaktor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentenversichert")
    private int rentenversichert;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arbeitslosenversicherungspflichtig")
    private int arbeitslosenversicherungspflichtig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kinderfreibetrag")
    private long kinderfreibetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "krankenversicherung")
    private double krankenversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kvzuschlag")
    private double kvzuschlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arbeitgeberzuschussKvPv")
    private double arbeitgeberzuschussKvPv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kinderlos")
    private int kinderlos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stelleImOsten")
    private int stelleImOsten;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stelleInSachsen")
    private int stelleInSachsen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kirchensteuer")
    private double kirchensteuer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lohnsteuerfreibetrag")
    private int lohnsteuerfreibetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hinzurechnungsbetrag")
    private int hinzurechnungsbetrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minijobGleizonenberechnung")
    private int minijobGleizonenberechnung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentenanwartschaft")
    private int rentenanwartschaft;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mitarbeiterid")
    private int mitarbeiterid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentenversicherung")
    private double rentenversicherung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pflegeversicherungallgemein")
    private double pflegeversicherungallgemein;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pflegeversicherungsachsen")
    private double pflegeversicherungsachsen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pflegeversicherungarbeitgeber")
    private double pflegeversicherungarbeitgeber;
    
    public Stammdaten() {
    }

    public Stammdaten(Integer id) {
        this.id = id;
    }

    public Stammdaten(Integer id, int steuerklasse, int ehegattenfaktor, int rentenversichert, int arbeitslosenversicherungspflichtig, long kinderfreibetrag, double krankenversicherung, double kvzuschlag, double arbeitgeberzuschussKvPv, int kinderlos, int stelleImOsten, int stelleInSachsen, double kirchensteuer, int lohnsteuerfreibetrag, int hinzurechnungsbetrag, int minijobGleizonenberechnung, int rentenanwartschaft, int mitarbeiterid, double rentenversicherung, double pflegeversicherungallgemein, double pflegeversicherungsachsen, double pflegeversicherungarbeitgeber) {
        this.id = id;
        this.steuerklasse = steuerklasse;
        this.ehegattenfaktor = ehegattenfaktor;
        this.rentenversichert = rentenversichert;
        this.arbeitslosenversicherungspflichtig = arbeitslosenversicherungspflichtig;
        this.kinderfreibetrag = kinderfreibetrag;
        this.krankenversicherung = krankenversicherung;
        this.kvzuschlag = kvzuschlag;
        this.arbeitgeberzuschussKvPv = arbeitgeberzuschussKvPv;
        this.kinderlos = kinderlos;
        this.stelleImOsten = stelleImOsten;
        this.stelleInSachsen = stelleInSachsen;
        this.kirchensteuer = kirchensteuer;
        this.lohnsteuerfreibetrag = lohnsteuerfreibetrag;
        this.hinzurechnungsbetrag = hinzurechnungsbetrag;
        this.minijobGleizonenberechnung = minijobGleizonenberechnung;
        this.rentenanwartschaft = rentenanwartschaft;
        this.mitarbeiterid = mitarbeiterid;
        this.rentenversicherung = rentenversicherung;
        this.pflegeversicherungallgemein = pflegeversicherungallgemein;
        this.pflegeversicherungsachsen = pflegeversicherungsachsen;
        this.pflegeversicherungarbeitgeber = pflegeversicherungarbeitgeber;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSteuerklasse() {
        return steuerklasse;
    }

    public void setSteuerklasse(int steuerklasse) {
        this.steuerklasse = steuerklasse;
    }

    public int getEhegattenfaktor() {
        return ehegattenfaktor;
    }

    public void setEhegattenfaktor(int ehegattenfaktor) {
        this.ehegattenfaktor = ehegattenfaktor;
    }

    public int getRentenversichert() {
        return rentenversichert;
    }

    public void setRentenversichert(int rentenversichert) {
        this.rentenversichert = rentenversichert;
    }

    public int getArbeitslosenversicherungspflichtig() {
        return arbeitslosenversicherungspflichtig;
    }

    public void setArbeitslosenversicherungspflichtig(int arbeitslosenversicherungspflichtig) {
        this.arbeitslosenversicherungspflichtig = arbeitslosenversicherungspflichtig;
    }

    public long getKinderfreibetrag() {
        return kinderfreibetrag;
    }

    public void setKinderfreibetrag(long kinderfreibetrag) {
        this.kinderfreibetrag = kinderfreibetrag;
    }

    public double getKrankenversicherung() {
        return krankenversicherung;
    }

    public void setKrankenversicherung(double krankenversicherung) {
        this.krankenversicherung = krankenversicherung;
    }

    public double getKvzuschlag() {
        return kvzuschlag;
    }

    public void setKvzuschlag(double kvzuschlag) {
        this.kvzuschlag = kvzuschlag;
    }

    public double getArbeitgeberzuschussKvPv() {
        return arbeitgeberzuschussKvPv;
    }

    public void setArbeitgeberzuschussKvPv(double arbeitgeberzuschussKvPv) {
        this.arbeitgeberzuschussKvPv = arbeitgeberzuschussKvPv;
    }

    public int getKinderlos() {
        return kinderlos;
    }

    public void setKinderlos(int kinderlos) {
        this.kinderlos = kinderlos;
    }

    public int getStelleImOsten() {
        return stelleImOsten;
    }

    public void setStelleImOsten(int stelleImOsten) {
        this.stelleImOsten = stelleImOsten;
    }

    public int getStelleInSachsen() {
        return stelleInSachsen;
    }

    public void setStelleInSachsen(int stelleInSachsen) {
        this.stelleInSachsen = stelleInSachsen;
    }

    public double getKirchensteuer() {
        return kirchensteuer;
    }

    public void setKirchensteuer(double kirchensteuer) {
        this.kirchensteuer = kirchensteuer;
    }

    public int getLohnsteuerfreibetrag() {
        return lohnsteuerfreibetrag;
    }

    public void setLohnsteuerfreibetrag(int lohnsteuerfreibetrag) {
        this.lohnsteuerfreibetrag = lohnsteuerfreibetrag;
    }

    public int getHinzurechnungsbetrag() {
        return hinzurechnungsbetrag;
    }

    public void setHinzurechnungsbetrag(int hinzurechnungsbetrag) {
        this.hinzurechnungsbetrag = hinzurechnungsbetrag;
    }

    public int getMinijobGleizonenberechnung() {
        return minijobGleizonenberechnung;
    }

    public void setMinijobGleizonenberechnung(int minijobGleizonenberechnung) {
        this.minijobGleizonenberechnung = minijobGleizonenberechnung;
    }

    public int getRentenanwartschaft() {
        return rentenanwartschaft;
    }

    public void setRentenanwartschaft(int rentenanwartschaft) {
        this.rentenanwartschaft = rentenanwartschaft;
    }

    public int getMitarbeiterid() {
        return mitarbeiterid;
    }

    public void setMitarbeiterid(int mitarbeiterid) {
        this.mitarbeiterid = mitarbeiterid;
    }

    public double getRentenversicherung() {
        return rentenversicherung;
    }

    public void setRentenversicherung(double rentenversicherung) {
        this.rentenversicherung = rentenversicherung;
    }

    public double getPflegeversicherungallgemein() {
        return pflegeversicherungallgemein;
    }

    public void setPflegeversicherungallgemein(double pflegeversicherungallgemein) {
        this.pflegeversicherungallgemein = pflegeversicherungallgemein;
    }

    public double getPflegeversicherungsachsen() {
        return pflegeversicherungsachsen;
    }

    public void setPflegeversicherungsachsen(double pflegeversicherungsachsen) {
        this.pflegeversicherungsachsen = pflegeversicherungsachsen;
    }

    public double getPflegeversicherungarbeitgeber() {
        return pflegeversicherungarbeitgeber;
    }

    public void setPflegeversicherungarbeitgeber(double pflegeversicherungarbeitgeber) {
        this.pflegeversicherungarbeitgeber = pflegeversicherungarbeitgeber;
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
        if (!(object instanceof Stammdaten)) {
            return false;
        }
        Stammdaten other = (Stammdaten) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Stammdaten[ id=" + id + " ]";
    }
    
}
