package com.alpcode.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

// Component annotation marks the class as a Spring bean
@Component
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("class instance created = " + this.getClass());
    }


    // define our init method
    @PostConstruct
    public void doAnythingOnStart(){
        System.out.println("doing something on start" + this.getClass());
    }

    // define our destroy method
    @PreDestroy
    public void doAnythingOnDestroy(){
        System.out.println("doing something on Destroy" + this.getClass());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes.";
    }
}
