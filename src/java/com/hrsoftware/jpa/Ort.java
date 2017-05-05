/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manu
 */
@Entity
@Table(catalog = "hrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ort.findAll", query = "SELECT o FROM Ort o")
    , @NamedQuery(name = "Ort.findByPlz", query = "SELECT o FROM Ort o WHERE o.plz = :plz")
    , @NamedQuery(name = "Ort.findByName", query = "SELECT o FROM Ort o WHERE o.name = :name")})
public class Ort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer plz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;


    public Ort() {
    }

    public Ort(Integer plz) {
        this.plz = plz;
    }

    public Ort(Integer plz, String name) {
        this.plz = plz;
        this.name = name;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plz != null ? plz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ort)) {
            return false;
        }
        Ort other = (Ort) object;
        if ((this.plz == null && other.plz != null) || (this.plz != null && !this.plz.equals(other.plz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Ort[ plz=" + plz + " ]";
    }
    
}
