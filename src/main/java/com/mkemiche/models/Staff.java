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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Staff {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private int id;
    private String name;

    public Staff(String name) {
        this.name = name;
    }
}
