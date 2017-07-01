/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrsoftware.jpaservice;

import com.hrsoftware.jpa.Permission;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Manu
 */
@Stateless
public class PermissionService extends AbstractService<Permission> {

    @PersistenceContext(unitName = "HRSoftwarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermissionService() {
        super(Permission.class);
    }
    
}
