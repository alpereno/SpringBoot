package com.alpcode.cruddemo.dao;

import com.alpcode.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // no need to write code here...
    // cause extends JpaRepository
    // Get the CRUD methods from there
}
