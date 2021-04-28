package com.mkemiche.dao;

import com.mkemiche.models.Employee;

import java.util.List;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
public interface IEmployee {

    void createEmployee(Employee e);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void updateEmployee(int id);
    void removeEmployee(int id);
    Employee getEmployeeByName(String name);
    List<Employee> searchBySalary(double salary);
}
