package com.alpcode.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("class instance created = " + this.getClass());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
