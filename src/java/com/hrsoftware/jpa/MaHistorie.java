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
@Table(name = "ma_historie", catalog = "hrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "MaHistorie.findAll", query = "SELECT m FROM MaHistorie m")
    , @NamedQuery(name = "MaHistorie.findById", query = "SELECT m FROM MaHistorie m WHERE m.id = :id")
    , @NamedQuery(name = "MaHistorie.findByAnfang", query = "SELECT m FROM MaHistorie m WHERE m.anfang = :anfang")
    , @NamedQuery(name = "MaHistorie.findByEnde", query = "SELECT m FROM MaHistorie m WHERE m.ende = :ende")})
public class MaHistorie implements Serializable {

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

    public MaHistorie() {
    }

    public MaHistorie(Integer id) {
        this.id = id;
    }

    public MaHistorie(Integer id, Date anfang, Date ende) {
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
        if (!(object instanceof MaHistorie)) {
            return false;
        }
        MaHistorie other = (MaHistorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.MaHistorie[ id=" + id + " ]";
    }
    
}
