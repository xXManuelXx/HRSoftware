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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manu
 */
@Entity
@Table(catalog = "hrsystem", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ma_id"})})
@NamedQueries({
    @NamedQuery(name = "Krankheit.findAll", query = "SELECT k FROM Krankheit k")
    , @NamedQuery(name = "Krankheit.findById", query = "SELECT k FROM Krankheit k WHERE k.id = :id")
    , @NamedQuery(name = "Krankheit.findByAnfang", query = "SELECT k FROM Krankheit k WHERE k.anfang = :anfang")
    , @NamedQuery(name = "Krankheit.findByEnde", query = "SELECT k FROM Krankheit k WHERE k.ende = :ende")
    , @NamedQuery(name = "Krankheit.findByAttest", query = "SELECT k FROM Krankheit k WHERE k.attest = :attest")})
public class Krankheit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date anfang;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ende;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean attest;
    @JoinColumn(name = "ma_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Mitarbeiter maId;

    public Krankheit() {
    }

    public Krankheit(Integer id) {
        this.id = id;
    }

    public Krankheit(Integer id, Date anfang, Date ende, boolean attest) {
        this.id = id;
        this.anfang = anfang;
        this.ende = ende;
        this.attest = attest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAnfang() {
        return anfang;
    }

    public void setAnfang(Date anfang) {
        this.anfang = anfang;
    }

    public Date getEnde() {
        return ende;
    }

    public void setEnde(Date ende) {
        this.ende = ende;
    }

    public boolean getAttest() {
        return attest;
    }

    public void setAttest(boolean attest) {
        this.attest = attest;
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
        if (!(object instanceof Krankheit)) {
            return false;
        }
        Krankheit other = (Krankheit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Krankheit[ id=" + id + " ]";
    }
    
}
