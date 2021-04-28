package com.mkemiche.manager;

import com.mkemiche.models.NonTeachingStaff;
import com.mkemiche.models.Staff;
import com.mkemiche.models.TeachingStaff;

/**
 * @author mkemiche
 * @created 27/04/2021
 */
public abstract class StaffManager {

    public static Object staffManager(String name, String qualification, String subjectExpertise) {
        return new TeachingStaff(name, qualification, subjectExpertise);
    }

    public static Object staffManager(String name, String areaExperience) {
        return new NonTeachingStaff(name, areaExperience);
    }
}
