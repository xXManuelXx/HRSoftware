/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpaservice;

import com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfehrenbach
 */
@Stateless
public class GehaltsabrechnungvariableeingabenFacade extends AbstractFacade<Gehaltsabrechnungvariableeingaben> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GehaltsabrechnungvariableeingabenFacade() {
        super(Gehaltsabrechnungvariableeingaben.class);
    }
    
}
