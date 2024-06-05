package com.alpcode.cruddemo.dao;

import com.alpcode.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
