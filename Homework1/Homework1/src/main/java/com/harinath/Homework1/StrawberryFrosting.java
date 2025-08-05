package com.harinath.Homework1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "env.flavour" , havingValue = "Strawberry")
public class StrawberryFrosting implements Frosting {
    @Override
    public String getFrostingType() {
        return "Strawberry Frosting";
    }
}
