package com.alpcode.cruddemo.service;

import com.alpcode.cruddemo.dao.EmployeeRepository;
import com.alpcode.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if (result.isPresent()){
            employee=result.get();
        }
        else{
            // didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return employee;
    }

    // delete @Transactional cause of JpaRepository handles it
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // delete @Transactional cause of JpaRepository handles it
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
