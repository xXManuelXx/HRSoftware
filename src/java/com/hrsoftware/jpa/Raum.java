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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manu
 */
@Entity
@Table(catalog = "hrsystem", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"abteilungs_id"})})
@NamedQueries({
    @NamedQuery(name = "Raum.findAll", query = "SELECT r FROM Raum r")
    , @NamedQuery(name = "Raum.findById", query = "SELECT r FROM Raum r WHERE r.id = :id")
    , @NamedQuery(name = "Raum.findByName", query = "SELECT r FROM Raum r WHERE r.name = :name")})
public class Raum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;
    @JoinColumn(name = "abteilungs_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Abteilung abteilungsId;

    public Raum() {
    }

    public Raum(Integer id) {
        this.id = id;
    }

    public Raum(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Abteilung getAbteilungsId() {
        return abteilungsId;
    }

    public void setAbteilungsId(Abteilung abteilungsId) {
        this.abteilungsId = abteilungsId;
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
        if (!(object instanceof Raum)) {
            return false;
        }
        Raum other = (Raum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Raum[ id=" + id + " ]";
    }
    
}
