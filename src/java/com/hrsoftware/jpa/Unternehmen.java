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
import javax.validation.constraints.Size;

/**
 *
 * @author mfehrenbach
 */
@Entity
@Table(name = "unternehmen")
@NamedQueries({
    @NamedQuery(name = "Unternehmen.findAll", query = "SELECT u FROM Unternehmen u")
    , @NamedQuery(name = "Unternehmen.findById", query = "SELECT u FROM Unternehmen u WHERE u.id = :id")
    , @NamedQuery(name = "Unternehmen.findByName", query = "SELECT u FROM Unternehmen u WHERE u.name = :name")
    , @NamedQuery(name = "Unternehmen.findByStrasse", query = "SELECT u FROM Unternehmen u WHERE u.strasse = :strasse")
    , @NamedQuery(name = "Unternehmen.findByOrt", query = "SELECT u FROM Unternehmen u WHERE u.ort = :ort")})
public class Unternehmen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "strasse")
    private String strasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ort")
    private int ort;

    public Unternehmen() {
    }

    public Unternehmen(Integer id) {
        this.id = id;
    }

    public Unternehmen(Integer id, String name, String strasse, int ort) {
        this.id = id;
        this.name = name;
        this.strasse = strasse;
        this.ort = ort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getOrt() {
        return ort;
    }

    public void setOrt(int ort) {
        this.ort = ort;
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
        if (!(object instanceof Unternehmen)) {
            return false;
        }
        Unternehmen other = (Unternehmen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Unternehmen[ id=" + id + " ]";
    }
    
}
