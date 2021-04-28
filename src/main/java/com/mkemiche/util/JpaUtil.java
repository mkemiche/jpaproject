package com.mkemiche.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
public class JpaUtil {
    private static final String NAME = "persistance_jpa";
    private static EntityManagerFactory emf;
    public static EntityManager getEntityManager(){
        emf = Persistence.createEntityManagerFactory(NAME);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    public static void closeEMF(){
        if(emf != null)
            emf.close();
    }

}
