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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mfehrenbach
 */
@Entity
@Table(name = "lohnkonto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lohnkonto.findAll", query = "SELECT l FROM Lohnkonto l")
    , @NamedQuery(name = "Lohnkonto.findById", query = "SELECT l FROM Lohnkonto l WHERE l.id = :id")
    , @NamedQuery(name = "Lohnkonto.findByMonat", query = "SELECT l FROM Lohnkonto l WHERE l.monat = :monat")
    , @NamedQuery(name = "Lohnkonto.findByBruttolohn", query = "SELECT l FROM Lohnkonto l WHERE l.bruttolohn = :bruttolohn")
    , @NamedQuery(name = "Lohnkonto.findByGeltwertevorteilelaufend", query = "SELECT l FROM Lohnkonto l WHERE l.geltwertevorteilelaufend = :geltwertevorteilelaufend")
    , @NamedQuery(name = "Lohnkonto.findByEinmalbezuegeimbruttolohn", query = "SELECT l FROM Lohnkonto l WHERE l.einmalbezuegeimbruttolohn = :einmalbezuegeimbruttolohn")
    , @NamedQuery(name = "Lohnkonto.findByGeltwertevorteileeinmalig", query = "SELECT l FROM Lohnkonto l WHERE l.geltwertevorteileeinmalig = :geltwertevorteileeinmalig")
    , @NamedQuery(name = "Lohnkonto.findBySteuerfreiebezuege", query = "SELECT l FROM Lohnkonto l WHERE l.steuerfreiebezuege = :steuerfreiebezuege")
    , @NamedQuery(name = "Lohnkonto.findByLohnsteuer", query = "SELECT l FROM Lohnkonto l WHERE l.lohnsteuer = :lohnsteuer")
    , @NamedQuery(name = "Lohnkonto.findBySolidaritaetszuschlag", query = "SELECT l FROM Lohnkonto l WHERE l.solidaritaetszuschlag = :solidaritaetszuschlag")
    , @NamedQuery(name = "Lohnkonto.findByKirchensteuer", query = "SELECT l FROM Lohnkonto l WHERE l.kirchensteuer = :kirchensteuer")
    , @NamedQuery(name = "Lohnkonto.findByKvan", query = "SELECT l FROM Lohnkonto l WHERE l.kvan = :kvan")
    , @NamedQuery(name = "Lohnkonto.findByKvzusatzbeitrag", query = "SELECT l FROM Lohnkonto l WHERE l.kvzusatzbeitrag = :kvzusatzbeitrag")
    , @NamedQuery(name = "Lohnkonto.findByRvan", query = "SELECT l FROM Lohnkonto l WHERE l.rvan = :rvan")
    , @NamedQuery(name = "Lohnkonto.findByAvan", query = "SELECT l FROM Lohnkonto l WHERE l.avan = :avan")
    , @NamedQuery(name = "Lohnkonto.findByPvan", query = "SELECT l FROM Lohnkonto l WHERE l.pvan = :pvan")
    , @NamedQuery(name = "Lohnkonto.findByKvag", query = "SELECT l FROM Lohnkonto l WHERE l.kvag = :kvag")
    , @NamedQuery(name = "Lohnkonto.findByRvag", query = "SELECT l FROM Lohnkonto l WHERE l.rvag = :rvag")
    , @NamedQuery(name = "Lohnkonto.findByAvag", query = "SELECT l FROM Lohnkonto l WHERE l.avag = :avag")
    , @NamedQuery(name = "Lohnkonto.findByPvag", query = "SELECT l FROM Lohnkonto l WHERE l.pvag = :pvag")
    , @NamedQuery(name = "Lohnkonto.findByUmlage1", query = "SELECT l FROM Lohnkonto l WHERE l.umlage1 = :umlage1")
    , @NamedQuery(name = "Lohnkonto.findByUmlage2", query = "SELECT l FROM Lohnkonto l WHERE l.umlage2 = :umlage2")
    , @NamedQuery(name = "Lohnkonto.findByInsolvenzumlage", query = "SELECT l FROM Lohnkonto l WHERE l.insolvenzumlage = :insolvenzumlage")
    , @NamedQuery(name = "Lohnkonto.findByBeitragzurbav", query = "SELECT l FROM Lohnkonto l WHERE l.beitragzurbav = :beitragzurbav")
    , @NamedQuery(name = "Lohnkonto.findByDavonsozvpflichtig", query = "SELECT l FROM Lohnkonto l WHERE l.davonsozvpflichtig = :davonsozvpflichtig")
    , @NamedQuery(name = "Lohnkonto.findBySteuerbrutto", query = "SELECT l FROM Lohnkonto l WHERE l.steuerbrutto = :steuerbrutto")
    , @NamedQuery(name = "Lohnkonto.findBySvbruttorv", query = "SELECT l FROM Lohnkonto l WHERE l.svbruttorv = :svbruttorv")
    , @NamedQuery(name = "Lohnkonto.findBySvbruttokv", query = "SELECT l FROM Lohnkonto l WHERE l.svbruttokv = :svbruttokv")
    , @NamedQuery(name = "Lohnkonto.findBySteuerBruttoSumMonth", query = "SELECT SUM(l.steuerbrutto) as sumSteuerBrutto FROM Lohnkonto l where l.maid = :maid ORDER BY l.monat ")
    , @NamedQuery(name = "Lohnkonto.findBySVBruttoRVSum", query = "SELECT SUM(l.svbruttorv) as svbruttorvsum FROM Lohnkonto l where l.maid = :maid")
    , @NamedQuery(name = "Lohnkonto.findBySVBruttoKVSum", query = "SELECT SUM(l.svbruttokv) as svbruttokvsum FROM Lohnkonto l where l.maid = :maid")
    , @NamedQuery(name = "Lohnkonto.findByJahresEinmalbezuege", query = "SELECT SUM(l.einmalbezuegeimbruttolohn) as einmalbezuegeimbruttolohnsum FROM Lohnkonto l where l.maid = :maid")
    , @NamedQuery(name = "Lohnkonto.findByMonatUndMitarbeiter", query = "SELECT l FROM Lohnkonto l WHERE l.monat = :monat AND l.maid = :maid")
    , @NamedQuery(name = "Lohnkonto.findByGesamtnetto", query = "SELECT l FROM Lohnkonto l WHERE l.gesamtnetto = :gesamtnetto")})
public class Lohnkonto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monat")
    private int monat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bruttolohn")
    private double bruttolohn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "geltwertevorteilelaufend")
    private double geltwertevorteilelaufend;
    @Basic(optional = false)
    @NotNull
    @Column(name = "einmalbezuegeimbruttolohn")
    private double einmalbezuegeimbruttolohn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "geltwertevorteileeinmalig")
    private double geltwertevorteileeinmalig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steuerfreiebezuege")
    private double steuerfreiebezuege;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lohnsteuer")
    private double lohnsteuer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solidaritaetszuschlag")
    private double solidaritaetszuschlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kirchensteuer")
    private double kirchensteuer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kvan")
    private double kvan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kvzusatzbeitrag")
    private double kvzusatzbeitrag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rvan")
    private double rvan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avan")
    private double avan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pvan")
    private double pvan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kvag")
    private double kvag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rvag")
    private double rvag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avag")
    private double avag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pvag")
    private double pvag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "umlage1")
    private double umlage1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "umlage2")
    private double umlage2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "insolvenzumlage")
    private double insolvenzumlage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "beitragzurbav")
    private double beitragzurbav;
    @Basic(optional = false)
    @NotNull
    @Column(name = "davonsozvpflichtig")
    private double davonsozvpflichtig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "steuerbrutto")
    private double steuerbrutto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "svbruttorv")
    private double svbruttorv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "svbruttokv")
    private double svbruttokv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gesamtnetto")
    private double gesamtnetto;
    @JoinColumn(name = "maid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mitarbeiter maid;


    
    public Lohnkonto() {
    }

    public Lohnkonto(Integer id) {
        this.id = id;
    }

    public Lohnkonto(Integer id, int monat, double bruttolohn, double geltwertevorteilelaufend, double einmalbezuegeimbruttolohn, double geltwertevorteileeinmalig, double steuerfreiebezuege, double lohnsteuer, double solidaritaetszuschlag, double kirchensteuer, double kvan, double kvzusatzbeitrag, double rvan, double avan, double pvan, double kvag, double rvag, double avag, double pvag, double umlage1, double umlage2, double insolvenzumlage, double beitragzurbav, double davonsozvpflichtig, double steuerbrutto, double svbruttorv, double svbruttokv, double gesamtnetto) {
        this.id = id;
        this.monat = monat;
        this.bruttolohn = bruttolohn;
        this.geltwertevorteilelaufend = geltwertevorteilelaufend;
        this.einmalbezuegeimbruttolohn = einmalbezuegeimbruttolohn;
        this.geltwertevorteileeinmalig = geltwertevorteileeinmalig;
        this.steuerfreiebezuege = steuerfreiebezuege;
        this.lohnsteuer = lohnsteuer;
        this.solidaritaetszuschlag = solidaritaetszuschlag;
        this.kirchensteuer = kirchensteuer;
        this.kvan = kvan;
        this.kvzusatzbeitrag = kvzusatzbeitrag;
        this.rvan = rvan;
        this.avan = avan;
        this.pvan = pvan;
        this.kvag = kvag;
        this.rvag = rvag;
        this.avag = avag;
        this.pvag = pvag;
        this.umlage1 = umlage1;
        this.umlage2 = umlage2;
        this.insolvenzumlage = insolvenzumlage;
        this.beitragzurbav = beitragzurbav;
        this.davonsozvpflichtig = davonsozvpflichtig;
        this.steuerbrutto = steuerbrutto;
        this.svbruttorv = svbruttorv;
        this.svbruttokv = svbruttokv;
        this.gesamtnetto = gesamtnetto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMonat() {
        return monat;
    }

    public void setMonat(int monat) {
        this.monat = monat;
    }

    public double getBruttolohn() {
        return bruttolohn;
    }

    public void setBruttolohn(double bruttolohn) {
        this.bruttolohn = bruttolohn;
    }

    public double getGeltwertevorteilelaufend() {
        return geltwertevorteilelaufend;
    }

    public void setGeltwertevorteilelaufend(double geltwertevorteilelaufend) {
        this.geltwertevorteilelaufend = geltwertevorteilelaufend;
    }

    public double getEinmalbezuegeimbruttolohn() {
        return einmalbezuegeimbruttolohn;
    }

    public void setEinmalbezuegeimbruttolohn(double einmalbezuegeimbruttolohn) {
        this.einmalbezuegeimbruttolohn = einmalbezuegeimbruttolohn;
    }

    public double getGeltwertevorteileeinmalig() {
        return geltwertevorteileeinmalig;
    }

    public void setGeltwertevorteileeinmalig(double geltwertevorteileeinmalig) {
        this.geltwertevorteileeinmalig = geltwertevorteileeinmalig;
    }

    public double getSteuerfreiebezuege() {
        return steuerfreiebezuege;
    }

    public void setSteuerfreiebezuege(double steuerfreiebezuege) {
        this.steuerfreiebezuege = steuerfreiebezuege;
    }

    public double getLohnsteuer() {
        return lohnsteuer;
    }

    public void setLohnsteuer(double lohnsteuer) {
        this.lohnsteuer = lohnsteuer;
    }

    public double getSolidaritaetszuschlag() {
        return solidaritaetszuschlag;
    }

    public void setSolidaritaetszuschlag(double solidaritaetszuschlag) {
        this.solidaritaetszuschlag = solidaritaetszuschlag;
    }

    public double getKirchensteuer() {
        return kirchensteuer;
    }

    public void setKirchensteuer(double kirchensteuer) {
        this.kirchensteuer = kirchensteuer;
    }

    public double getKvan() {
        return kvan;
    }

    public void setKvan(double kvan) {
        this.kvan = kvan;
    }

    public double getKvzusatzbeitrag() {
        return kvzusatzbeitrag;
    }

    public void setKvzusatzbeitrag(double kvzusatzbeitrag) {
        this.kvzusatzbeitrag = kvzusatzbeitrag;
    }

    public double getRvan() {
        return rvan;
    }

    public void setRvan(double rvan) {
        this.rvan = rvan;
    }

    public double getAvan() {
        return avan;
    }

    public void setAvan(double avan) {
        this.avan = avan;
    }

    public double getPvan() {
        return pvan;
    }

    public void setPvan(double pvan) {
        this.pvan = pvan;
    }

    public double getKvag() {
        return kvag;
    }

    public void setKvag(double kvag) {
        this.kvag = kvag;
    }

    public double getRvag() {
        return rvag;
    }

    public void setRvag(double rvag) {
        this.rvag = rvag;
    }

    public double getAvag() {
        return avag;
    }

    public void setAvag(double avag) {
        this.avag = avag;
    }

    public double getPvag() {
        return pvag;
    }

    public void setPvag(double pvag) {
        this.pvag = pvag;
    }

    public double getUmlage1() {
        return umlage1;
    }

    public void setUmlage1(double umlage1) {
        this.umlage1 = umlage1;
    }

    public double getUmlage2() {
        return umlage2;
    }

    public void setUmlage2(double umlage2) {
        this.umlage2 = umlage2;
    }

    public double getInsolvenzumlage() {
        return insolvenzumlage;
    }

    public void setInsolvenzumlage(double insolvenzumlage) {
        this.insolvenzumlage = insolvenzumlage;
    }

    public double getBeitragzurbav() {
        return beitragzurbav;
    }

    public void setBeitragzurbav(double beitragzurbav) {
        this.beitragzurbav = beitragzurbav;
    }

    public double getDavonsozvpflichtig() {
        return davonsozvpflichtig;
    }

    public void setDavonsozvpflichtig(double davonsozvpflichtig) {
        this.davonsozvpflichtig = davonsozvpflichtig;
    }

    public double getSteuerbrutto() {
        return steuerbrutto;
    }

    public void setSteuerbrutto(double steuerbrutto) {
        this.steuerbrutto = steuerbrutto;
    }

    public double getSvbruttorv() {
        return svbruttorv;
    }

    public void setSvbruttorv(double svbruttorv) {
        this.svbruttorv = svbruttorv;
    }

    public double getSvbruttokv() {
        return svbruttokv;
    }

    public void setSvbruttokv(double svbruttokv) {
        this.svbruttokv = svbruttokv;
    }

    public double getGesamtnetto() {
        return gesamtnetto;
    }

    public void setGesamtnetto(double gesamtnetto) {
        this.gesamtnetto = gesamtnetto;
    }

    public Mitarbeiter getMaid() {
        return maid;
    }

    public void setMaid(Mitarbeiter maid) {
        this.maid = maid;
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
        if (!(object instanceof Lohnkonto)) {
            return false;
        }
        Lohnkonto other = (Lohnkonto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpa.Lohnkonto[ id=" + id + " ]";
    }
    
}
