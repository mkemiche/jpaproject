package com.mkemiche.services;

import com.mkemiche.MainRunner;
import com.mkemiche.dao.IEmployee;
import com.mkemiche.models.Employee;
import com.mkemiche.util.JpaUtil;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
@Log4j
public class EmployeeService implements IEmployee {
    EntityManager em = null;
    @Override
    public void createEmployee(Employee e) {
        if(getEmployeeById(e.getId()) != null){
           log.log(Level.WARN,"Employee already exists !");
           return;
        }
        try{
            em = JpaUtil.getEntityManager();
            em.persist(e);
            em.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex){
            ex.getStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {

        Employee emp = null;
        try {
            em = JpaUtil.getEntityManager();
            emp = em.find(Employee.class, id);
            em.getTransaction().commit();
        }catch (IllegalArgumentException | EntityNotFoundException ex){
            ex.getStackTrace();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
        return emp;
    }

    public Employee findById(int id){
        Employee emp = null;
        try{
            em = JpaUtil.getEntityManager();
            emp =  (Employee) em.createQuery("select e from Employee e where e.id = :id").setParameter("id", id).getSingleResult();
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.getStackTrace();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }

        return emp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        em = JpaUtil.getEntityManager();
        List<Employee> empList = new ArrayList<>();
        try{
            empList = em.createQuery("select e from Employee e").getResultList();
            em.getTransaction().commit();
        }catch(RuntimeException ex){
            log.error(String.format("Something wrong on %s cause %s", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
        return empList;
    }

    @Override
    public void updateEmployee(int id) {
        Employee emp = findById(id);
        if(emp == null){
            log.info("Cannot remove - staff doesn't exists");
            return;
        }
        emp = MainRunner.getEmployee();
        try{
            em = JpaUtil.getEntityManager();
            em.persist(emp);
            em.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex){
            ex.getStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
    }

    @Override
    public void removeEmployee(int id) {
        Employee emp = getEmployeeById(id);
        if(emp == null){
            log.info("Cannot remove - Employee doesn't not exists");
            return;
        }
        try {
            em = JpaUtil.getEntityManager();
            Employee emp2 = em.merge(emp);
            em.remove(emp2);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            log.error(String.format("%s - %s ", ex.getStackTrace()[0].getMethodName(), ex.getCause()));
            em.getTransaction().rollback();
        } finally {
            em.close();
            JpaUtil.closeEMF();
        }
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Employee emp = null;
        try{
            em = JpaUtil.getEntityManager();
            emp =  (Employee) em.createNamedQuery("getByName").setParameter("name", name).getSingleResult();
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.getStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }

        return emp;
    }

    @Override
    public List<Employee> searchBySalary(double salary) {
        List<Employee> employeeList = null;
        try{
            em = JpaUtil.getEntityManager();
            employeeList = em.createNamedQuery("searchBySalary").setParameter("salary", salary).getResultList();
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.getStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
            JpaUtil.closeEMF();
        }
        return employeeList;
    }
}
