package com.mkemiche.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

/**
 * @author mkemiche
 * @created 27/04/2021
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({@NamedQuery(name = "getByName", query = "SELECT e FROM Employee e Where e.name = :name"),
                @NamedQuery(name = "searchBySalary", query = "SELECT e from Employee e where e.salary > :salary")})
public class Employee implements Comparable<Employee>{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private int id;
    private String name;
    private double salary;
    private String dept;

    public Employee(String name, double salary, String dept) {
        this.name = name;
        this.salary = salary;
        this.dept = dept;
    }

    @Override
    public int compareTo(Employee o) {
        if(this.salary > o.salary) return 1;
        if(this.salary <o.salary) return -1;
        return 0;
    }
}
