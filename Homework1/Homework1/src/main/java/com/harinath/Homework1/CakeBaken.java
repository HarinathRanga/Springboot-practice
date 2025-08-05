package com.harinath.Homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaken {

    @Autowired
    Frosting frosting;

    @Autowired
    Syrup syrup;

    public void bakeCake() {
        System.out.println(frosting.getFrostingType()+" Used");
        System.out.println(syrup.getSyrupType()+" Used");
        System.out.println("Cake is ready!!!!!");
    }


}
