package com.alpcode.demo.rest;

import com.alpcode.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    ArrayList<Student> students;

    // define postConstruct to load the student data... only once!
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Sonic", "The Hedgehog"));
        students.add(new Student("Shadow", "Hedgehog"));
        students.add(new Student("Yugi", "Muto"));
        students.add(new Student("Atem", "Muto"));
    }

    // define end point for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    // define end point for "students/{studentId}" - return student at index
    // it have to be same with parameters name and mapping name that in the url section
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // check the studentId again list size
        if (studentId  >= students.size() || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }

}
