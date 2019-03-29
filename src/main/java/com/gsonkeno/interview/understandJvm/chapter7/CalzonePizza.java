package com.gsonkeno.interview.jvm.understandJvm.chapter7;

@Factory(type=CalzonePizza.class, id="Calzone")
public class CalzonePizza implements Meal{

    @Override
    public float getPrice() {
        return 8.5f;
    }
}
