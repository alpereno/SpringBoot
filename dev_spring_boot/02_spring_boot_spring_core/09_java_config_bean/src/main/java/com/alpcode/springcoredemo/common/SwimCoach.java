package com.alpcode.springcoredemo.common;

// Don't use @Component annotation our purpose is using this with @Bean
public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("class instance created = " + this.getClass());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim x meters as a warm up";
    }
}
