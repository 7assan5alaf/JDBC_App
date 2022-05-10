package org.example.jdbccourse.dao;

import org.example.jdbccourse.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    Employee findById(int id);
    void deleteById(int id);
    void save(Employee e);

}
