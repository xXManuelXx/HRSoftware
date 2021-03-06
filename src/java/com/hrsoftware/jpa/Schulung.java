/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Schulung.findAll", query = "SELECT s FROM Schulung s")
    , @NamedQuery(name = "Schulung.findById", query = "SELECT s FROM Schulung s WHERE s.id = :id")
    , @NamedQuery(name = "Schulung.findByKosten", query = "SELECT s FROM Schulung s WHERE s.kosten = :kosten")})
public class Schulung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String beschreibung;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long kosten;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schulung")
    private Collection<MaSchulung> maSchulungCollection;

    public Schulung() {
    }

    public Schulung(Integer id) {
        this.id = id;
    }

    public Schulung(Integer id, String beschreibung, long kosten) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.kosten = kosten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public long getKosten() {
        return kosten;
    }

    public void setKosten(long kosten) {
        this.kosten = kosten;
    }

    public Collection<MaSchulung> getMaSchulungCollection() {
        return maSchulungCollection;
    }

    public void setMaSchulungCollection(Collection<MaSchulung> maSchulungCollection) {
        this.maSchulungCollection = maSchulungCollection;
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
        if (!(object instanceof Schulung)) {
            return false;
        }
        Schulung other = (Schulung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.Schulung[ id=" + id + " ]";
    }
    
}
