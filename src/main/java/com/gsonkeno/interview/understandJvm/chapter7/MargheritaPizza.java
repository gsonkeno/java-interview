package com.gsonkeno.interview.jvm.understandJvm.chapter7;

@Factory(type=MargheritaPizza.class, id="Margherita")
public class MargheritaPizza implements Meal{

    @Override
    public float getPrice() {
        return 6.0f;
    }
}
