/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpaservice;

import com.hrsoftware.jpa.Gehalt;
import com.hrsoftware.jpa.Lohnkonto;
import com.hrsoftware.jpa.Mitarbeiter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfehrenbach
 */
@Stateless
public class LohnkontoFacade extends AbstractFacade<Lohnkonto> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LohnkontoFacade() {
        super(Lohnkonto.class);
    }

    public Lohnkonto getLohnkontoDatenVonMonat(int monat) {
        try {
            return (Lohnkonto) getEntityManager().createNamedQuery("Lohnkonto.findByMonat").setParameter("monat", monat).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Lohnkonto getLohnkontoDatenVonMonatUndMaId(int monat, Mitarbeiter maid) {
        try {
            return (Lohnkonto) getEntityManager().createNamedQuery("Lohnkonto.findByMonatUndMitarbeiter").setParameter("monat", monat).setParameter("maid", maid).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public double getSumSteuerBruttoBisher(Mitarbeiter ma, int month) {
        try {
            return (double) getEntityManager().createNamedQuery("Lohnkonto.findBySteuerBruttoSumMonth").setParameter("maid", ma).setMaxResults(month).getSingleResult();
        } catch (NullPointerException e) {
            return 0.0;
        }

    }

    public double getSumSVBruttoRV(Mitarbeiter maid) {
        try {
            return (double) getEntityManager().createNamedQuery("Lohnkonto.findBySVBruttoRVSum").setParameter("maid", maid).getSingleResult();

        } catch (NullPointerException e) {
            return 0.0;
        }

    }

    public double getSumSVBruttoKV(Mitarbeiter maid) {
        try {
            return (double) getEntityManager().createNamedQuery("Lohnkonto.findBySVBruttoKVSum").setParameter("maid", maid).getSingleResult();
        } catch (NullPointerException e) {
            return 0.0;
        }
    }

    public double getEinmalbezuegeimbruttolohn(Mitarbeiter maid) {
        try {
            return (double) getEntityManager().createNamedQuery("Lohnkonto.findByJahresEinmalbezuege").setParameter("maid", maid).getSingleResult();
        } catch (NullPointerException e) {
            return 0.0;
        }
    }
}
