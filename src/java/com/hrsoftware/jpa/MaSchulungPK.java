/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manu
 */
@Embeddable
public class MaSchulungPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "schulung_id", nullable = false)
    private int schulungId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_id", nullable = false)
    private int maId;

    public MaSchulungPK() {
    }

    public MaSchulungPK(int schulungId, int maId) {
        this.schulungId = schulungId;
        this.maId = maId;
    }

    public int getSchulungId() {
        return schulungId;
    }

    public void setSchulungId(int schulungId) {
        this.schulungId = schulungId;
    }

    public int getMaId() {
        return maId;
    }

    public void setMaId(int maId) {
        this.maId = maId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) schulungId;
        hash += (int) maId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaSchulungPK)) {
            return false;
        }
        MaSchulungPK other = (MaSchulungPK) object;
        if (this.schulungId != other.schulungId) {
            return false;
        }
        if (this.maId != other.maId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrsoftware.jpa.MaSchulungPK[ schulungId=" + schulungId + ", maId=" + maId + " ]";
    }
    
}
