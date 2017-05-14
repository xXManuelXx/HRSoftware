/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpacontroller;

import com.hrsoftware.jpa.Gehalt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Manu
 */
@Stateless
public class GehaltFacade extends AbstractFacade<Gehalt> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GehaltFacade() {
        super(Gehalt.class);
    }
    
    public Gehalt findGehaltByID(int id){
        return (Gehalt) getEntityManager().createNamedQuery("Gehalt.findById").setParameter("id",id).getSingleResult();     
    }
}
