package com.mkemiche.dao;

import com.mkemiche.models.Employee;

import java.util.List;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
public interface IStaff<T> {

    void createStaff(T s);
    T getStaffById(int id);
    List<T> getAllStaff();
    void updateStaff(T s);
    void removeStaff(int id);
}
