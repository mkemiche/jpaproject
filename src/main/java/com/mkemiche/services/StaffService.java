package com.mkemiche.services;

import com.mkemiche.MainRunner;
import com.mkemiche.dao.IStaff;
import com.mkemiche.manager.StaffManager;
import com.mkemiche.models.Employee;
import com.mkemiche.models.NonTeachingStaff;
import com.mkemiche.models.Staff;
import com.mkemiche.models.TeachingStaff;
import com.mkemiche.util.JpaUtil;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.hibernate.loader.collection.OneToManyJoinWalker;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
@Log4j
public class StaffService implements IStaff<Object> {

    EntityManager em = null;

    @Override
    public void createStaff(Object o) {
        Staff staff = null;
        if(o instanceof TeachingStaff){
            staff = (TeachingStaff) o;
        }
        if(o instanceof NonTeachingStaff){
            staff = (NonTeachingStaff) o;
        }
        try{
            em = JpaUtil.getEntityManager();
            em.persist(staff);
            em.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex){
            log.error(String.format("Something wrong on %s cause %s", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }

    }

    @Override
    public Object getStaffById(int id) {
        Staff st = null;
        try{
            em = JpaUtil.getEntityManager();
            st = (Staff) em.createQuery("select s from Staff s where s.id = :id").setParameter("id", id).getSingleResult();
            em.getTransaction().commit();
        }catch (Exception ex){
            log.error(String.format("Something wrong on %s cause %s", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }

        return st;
    }

    @Override
    public List<Object> getAllStaff() {
        em = JpaUtil.getEntityManager();
        List<Object> staffList = new ArrayList<>();
        try{
            staffList = em.createQuery("select e from Staff e").getResultList();
            em.getTransaction().commit();
        }catch(RuntimeException ex){
            log.error(String.format("Something wrong on %s cause %s", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
        return staffList;
    }

    @Override
    public void updateStaff(Object o) {

        Object staff = MainRunner.getObjectStaff();
        if(o instanceof TeachingStaff){
            TeachingStaff ts = (TeachingStaff) o;
            ((TeachingStaff)staff).setName(ts.getName());
            ((TeachingStaff)staff).setQualification(ts.getQualification());
            ((TeachingStaff)staff).setSubjectExpertise(ts.getSubjectExpertise());
        }
        if(o instanceof NonTeachingStaff){
            NonTeachingStaff nst = (NonTeachingStaff) o;
            ((NonTeachingStaff)staff).setName(nst.getName());
            ((NonTeachingStaff)staff).setAreaExperience(nst.getAreaExperience());
        }
        try{
            em = JpaUtil.getEntityManager();
            em.persist(staff);
            em.getTransaction().commit();
            log.info("updating staff with success");
        }catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex){
            log.error(String.format("%s - %s ", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
    }

    @Override
    public void removeStaff(int id) {
        Staff staff = (Staff) getStaffById(id);
        if(staff == null){
            log.info("Cannot remove - staff doesn't exists");
            return;
        }
        try {
            em = JpaUtil.getEntityManager();
            Staff st = em.merge(staff);
            em.remove(st);
            em.getTransaction().commit();
            log.info("removing staff with success");
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            log.error(String.format("%s - %s ", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        } finally {
            em.close();
            JpaUtil.closeEMF();
        }
    }
}
