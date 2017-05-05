/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manu
 */
@Entity
@Table(name = "ma_schulung", catalog = "hrsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "MaSchulung.findAll", query = "SELECT m FROM MaSchulung m")
    , @NamedQuery(name = "MaSchulung.findBySchulungId", query = "SELECT m FROM MaSchulung m WHERE m.maSchulungPK.schulungId = :schulungId")
    , @NamedQuery(name = "MaSchulung.findByMaId", query = "SELECT m FROM MaSchulung m WHERE m.maSchulungPK.maId = :maId")
    , @NamedQuery(name = "MaSchulung.findByAbgeschlossen", query = "SELECT m FROM MaSchulung m WHERE m.abgeschlossen = :abgeschlossen")})
public class MaSchulung implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaSchulungPK maSchulungPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean abgeschlossen;
    @JoinColumn(name = "ma_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mitarbeiter mitarbeiter;
    @JoinColumn(name = "schulung_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Schulung schulung;

    public MaSchulung() {
    }

    public MaSchulung(MaSchulungPK maSchulungPK) {
        this.maSchulungPK = maSchulungPK;
    }

    public MaSchulung(MaSchulungPK maSchulungPK, boolean abgeschlossen) {
        this.maSchulungPK = maSchulungPK;
        this.abgeschlossen = abgeschlossen;
    }

    public MaSchulung(int schulungId, int maId) {
        this.maSchulungPK = new MaSchulungPK(schulungId, maId);
    }

    public MaSchulungPK getMaSchulungPK() {
        return maSchulungPK;
    }

    public void setMaSchulungPK(MaSchulungPK maSchulungPK) {
        this.maSchulungPK = maSchulungPK;
    }

    public boolean getAbgeschlossen() {
        return abgeschlossen;
    }

    public void setAbgeschlossen(boolean abgeschlossen) {
        this.abgeschlossen = abgeschlossen;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Schulung getSchulung() {
        return schulung;
    }

    public void setSchulung(Schulung schulung) {
        this.schulung = schulung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maSchulungPK != null ? maSchulungPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaSchulung)) {
            return false;
        }
        MaSchulung other = (MaSchulung) object;
        if ((this.maSchulungPK == null && other.maSchulungPK != null) || (this.maSchulungPK != null && !this.maSchulungPK.equals(other.maSchulungPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.MaSchulung[ maSchulungPK=" + maSchulungPK + " ]";
    }
    
}
