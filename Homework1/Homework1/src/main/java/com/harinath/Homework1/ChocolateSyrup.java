package com.harinath.Homework1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "env.flavour" , havingValue = "Chocolate")
public class ChocolateSyrup implements Syrup {
    @Override
    public String getSyrupType() {
        return "Chocolate Syrup";
    }
}
