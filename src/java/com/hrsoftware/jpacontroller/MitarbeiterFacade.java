/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpacontroller;

import com.hrsoftware.jpa.Mitarbeiter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Manu
 */
@Stateless
public class MitarbeiterFacade extends AbstractFacade<Mitarbeiter> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public MitarbeiterFacade() {
        super(Mitarbeiter.class);
    }
    
}
