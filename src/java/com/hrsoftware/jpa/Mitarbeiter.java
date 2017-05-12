/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(catalog = "hrsystem", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_gehalt"})
    , @UniqueConstraint(columnNames = {"land"})
    , @UniqueConstraint(columnNames = {"id_user"})
    , @UniqueConstraint(columnNames = {"positions_id"})
    , @UniqueConstraint(columnNames = {"plz"})
    , @UniqueConstraint(columnNames = {"abteilungs_id"})
    , @UniqueConstraint(columnNames = {"info_id"})})
@NamedQueries({
    @NamedQuery(name = "Mitarbeiter.findAll", query = "SELECT m FROM Mitarbeiter m")
    , @NamedQuery(name = "Mitarbeiter.findById", query = "SELECT m FROM Mitarbeiter m WHERE m.id = :id")
    , @NamedQuery(name = "Mitarbeiter.findByNachname", query = "SELECT m FROM Mitarbeiter m WHERE m.nachname = :nachname")
    , @NamedQuery(name = "Mitarbeiter.findByVorname", query = "SELECT m FROM Mitarbeiter m WHERE m.vorname = :vorname")
    , @NamedQuery(name = "Mitarbeiter.findByGeburtsdatum", query = "SELECT m FROM Mitarbeiter m WHERE m.geburtsdatum = :geburtsdatum")
    , @NamedQuery(name = "Mitarbeiter.findByUrlaubstage", query = "SELECT m FROM Mitarbeiter m WHERE m.urlaubstage = :urlaubstage")
    , @NamedQuery(name = "Mitarbeiter.findBySteuerklasse", query = "SELECT m FROM Mitarbeiter m WHERE m.steuerklasse = :steuerklasse")})
public class Mitarbeiter implements Serializable {

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
    private String nachname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String vorname;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int urlaubstage;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int steuerklasse;
    @ManyToMany(mappedBy = "mitarbeiterCollection")
    private Collection<Skills> skillsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mitarbeiter")
    private Collection<MaSchulung> maSchulungCollection;
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private User idUser;
    @JoinColumn(name = "land", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Land land;
    @JoinColumn(name = "id_gehalt", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Gehalt idGehalt;
    @JoinColumn(name = "info_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Info infoId;
    @JoinColumn(name = "plz", referencedColumnName = "plz", nullable = false)
    @OneToOne(optional = false)
    private Ort plz;
    @JoinColumn(name = "abteilungs_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Abteilung abteilungsId;
    @JoinColumn(name = "positions_id", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Position positionsId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "maId")
    private Arbeitszeit arbeitszeit;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "maId")
    private Krankheit krankheit;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "maId")
    private MaHistorie maHistorie;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "maId")
    private Urlaub urlaub;

    public Mitarbeiter() {
    }

    public Mitarbeiter(Integer id) {
        this.id = id;
    }

    public Mitarbeiter(Integer id, String nachname, String vorname, Date geburtsdatum, int urlaubstage, int steuerklasse) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.urlaubstage = urlaubstage;
        this.steuerklasse = steuerklasse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getUrlaubstage() {
        return urlaubstage;
    }

    public void setUrlaubstage(int urlaubstage) {
        this.urlaubstage = urlaubstage;
    }

    public int getSteuerklasse() {
        return steuerklasse;
    }

    public void setSteuerklasse(int steuerklasse) {
        this.steuerklasse = steuerklasse;
    }

    public Collection<Skills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<Skills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public Collection<MaSchulung> getMaSchulungCollection() {
        return maSchulungCollection;
    }

    public void setMaSchulungCollection(Collection<MaSchulung> maSchulungCollection) {
        this.maSchulungCollection = maSchulungCollection;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Gehalt getIdGehalt() {
        return idGehalt;
    }

    public void setIdGehalt(Gehalt idGehalt) {
        this.idGehalt = idGehalt;
    }

    public Info getInfoId() {
        return infoId;
    }

    public void setInfoId(Info infoId) {
        this.infoId = infoId;
    }

    public Ort getPlz() {
        return plz;
    }

    public void setPlz(Ort plz) {
        this.plz = plz;
    }

    public Abteilung getAbteilungsId() {
        return abteilungsId;
    }

    public void setAbteilungsId(Abteilung abteilungsId) {
        this.abteilungsId = abteilungsId;
    }

    public Position getPositionsId() {
        return positionsId;
    }

    public void setPositionsId(Position positionsId) {
        this.positionsId = positionsId;
    }

    public Arbeitszeit getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(Arbeitszeit arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public Krankheit getKrankheit() {
        return krankheit;
    }

    public void setKrankheit(Krankheit krankheit) {
        this.krankheit = krankheit;
    }

    public MaHistorie getMaHistorie() {
        return maHistorie;
    }

    public void setMaHistorie(MaHistorie maHistorie) {
        this.maHistorie = maHistorie;
    }

    public Urlaub getUrlaub() {
        return urlaub;
    }

    public void setUrlaub(Urlaub urlaub) {
        this.urlaub = urlaub;
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
        if (!(object instanceof Mitarbeiter)) {
            return false;
        }
        Mitarbeiter other = (Mitarbeiter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.vorname + " " + this.nachname;
    }
    
}
