package com.alpcode.springcoredemo.common;

import org.springframework.stereotype.Component;

// Component annotation marks the class as a Spring bean
@Component
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("class instance created = " + this.getClass());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes.";
    }
}
