package com.mkemiche.models;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author mkemiche
 * @created 27/04/2021
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@DiscriminatorValue("NS")
public class NonTeachingStaff extends Staff{

    private String areaExperience;

    public NonTeachingStaff(String name, String areaExperience) {
        super(name);
        this.areaExperience = areaExperience;
    }
}
