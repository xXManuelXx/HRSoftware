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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manu
 */
@Entity
@Table(catalog = "hrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Gehalt.findAll", query = "SELECT g FROM Gehalt g")
    , @NamedQuery(name = "Gehalt.findById", query = "SELECT g FROM Gehalt g WHERE g.id = :id")
    , @NamedQuery(name = "Gehalt.findByGehalt", query = "SELECT g FROM Gehalt g WHERE g.gehalt = :gehalt")})
public class Gehalt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int gehalt;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idGehalt")
    private Mitarbeiter mitarbeiter;

    public Gehalt() {
    }

    public Gehalt(Integer id) {
        this.id = id;
    }

    public Gehalt(Integer id, int gehalt) {
        this.id = id;
        this.gehalt = gehalt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGehalt() {
        return gehalt;
    }

    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
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
        if (!(object instanceof Gehalt)) {
            return false;
        }
        Gehalt other = (Gehalt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Gehalt[ id=" + id + " ]";
    }
    
}
