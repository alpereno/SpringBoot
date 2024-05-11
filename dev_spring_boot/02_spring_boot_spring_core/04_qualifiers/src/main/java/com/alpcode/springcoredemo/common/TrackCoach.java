package com.alpcode.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrackCoach implements Coach{

    public TrackCoach(){
        System.out.println("class instance created = " + this.getClass());
    }
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
