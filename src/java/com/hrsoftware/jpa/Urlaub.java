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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manu
 */
@Entity
@Table(catalog = "hrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Urlaub.findAll", query = "SELECT u FROM Urlaub u")
    , @NamedQuery(name = "Urlaub.findById", query = "SELECT u FROM Urlaub u WHERE u.id = :id")
    , @NamedQuery(name = "Urlaub.findByAnfang", query = "SELECT u FROM Urlaub u WHERE u.anfang = :anfang")
    , @NamedQuery(name = "Urlaub.findByEnde", query = "SELECT u FROM Urlaub u WHERE u.ende = :ende")})
public class Urlaub implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String beschreibung;
    @JoinColumn(name = "ma_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Mitarbeiter maId;

    public Urlaub() {
    }

    public Urlaub(Integer id) {
        this.id = id;
    }

    public Urlaub(Integer id, Date anfang, Date ende, String beschreibung) {
        this.id = id;
        this.anfang = anfang;
        this.ende = ende;
        this.beschreibung = beschreibung;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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
        if (!(object instanceof Urlaub)) {
            return false;
        }
        Urlaub other = (Urlaub) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Urlaub[ id=" + id + " ]";
    }
    
}
