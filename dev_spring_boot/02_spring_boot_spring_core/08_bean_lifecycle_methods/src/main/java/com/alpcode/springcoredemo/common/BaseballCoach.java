package com.alpcode.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach(){
        System.out.println("class instance created = " + this.getClass());
    }
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }
}
