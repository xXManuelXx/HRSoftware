/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpaservice;

import com.hrsoftware.jpa.Unternehmen;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfehrenbach
 */
@Stateless
public class UnternehmenService extends AbstractService<Unternehmen> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnternehmenService() {
        super(Unternehmen.class);
    }
    
}
