/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpaservice;

import com.hrsoftware.jpa.Stammdaten;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfehrenbach
 */
@Stateless
public class StammdatenFacade extends AbstractFacade<Stammdaten> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StammdatenFacade() {
        super(Stammdaten.class);
    }
    
    public Stammdaten getBaseDataFromEmployeeId(int id){
        return (Stammdaten) getEntityManager().createNamedQuery("Stammdaten.findByMitarbeiterid").setParameter("mitarbeiterid",id).getSingleResult();     
    }
}
