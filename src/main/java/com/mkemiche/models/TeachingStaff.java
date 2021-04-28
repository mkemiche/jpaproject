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
@DiscriminatorValue("TS")
public class TeachingStaff extends Staff{

    private String subjectExpertise;
    private String qualification;
    public TeachingStaff(String name, String qualification, String subjectExpertise) {
        super(name);
        this.subjectExpertise = subjectExpertise;
        this.qualification = qualification;
    }
}
