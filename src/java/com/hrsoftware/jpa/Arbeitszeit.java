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
    @NamedQuery(name = "Arbeitszeit.findAll", query = "SELECT a FROM Arbeitszeit a")
    , @NamedQuery(name = "Arbeitszeit.findById", query = "SELECT a FROM Arbeitszeit a WHERE a.id = :id")
    , @NamedQuery(name = "Arbeitszeit.findByAnfang", query = "SELECT a FROM Arbeitszeit a WHERE a.anfang = :anfang")
    , @NamedQuery(name = "Arbeitszeit.findByEnde", query = "SELECT a FROM Arbeitszeit a WHERE a.ende = :ende")})
public class Arbeitszeit implements Serializable {

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
    @JoinColumn(name = "ma_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Mitarbeiter maId;

    public Arbeitszeit() {
    }

    public Arbeitszeit(Integer id) {
        this.id = id;
    }

    public Arbeitszeit(Integer id, Date anfang, Date ende) {
        this.id = id;
        this.anfang = anfang;
        this.ende = ende;
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
        if (!(object instanceof Arbeitszeit)) {
            return false;
        }
        Arbeitszeit other = (Arbeitszeit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Arbeitszeit[ id=" + id + " ]";
    }
    
}
