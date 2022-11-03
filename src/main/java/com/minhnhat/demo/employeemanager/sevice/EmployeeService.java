package com.minhnhat.demo.employeemanager.sevice;

import com.minhnhat.demo.employeemanager.model.Employee;
import com.minhnhat.demo.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> listEmp = repository.findAll();
        return listEmp;
    }

    public Optional<Employee> getEmployeeById(String id) {
        Optional<Employee> employee = repository.findById(id);
        return employee;
    }

    public Employee createNewEmployee(Employee employee) {
        Employee _emloyee = new Employee(
                employee.getFirstName(), employee.getLastName(), employee.getAge(), employee.getRole(), employee.getAddress() );
        repository.save(_emloyee);
        return _emloyee;
    }

    public Employee updateEmployee(String id, Employee employee) {
        Optional<Employee> employeeData = repository.findById(id);
        if(employeeData.isPresent()) {
            Employee _employee = employeeData.get();
            _employee.setFirstName(employee.getFirstName());
            _employee.setLastName(employee.getLastName());
            _employee.setAge(employee.getAge());
            _employee.setRole(employee.getRole());
            _employee.setAddress(employee.getAddress());
            repository.save(_employee);
            return _employee;
        }
        return null;
    }
    public String deleteOne(String id) {
        repository.deleteById(id);
        return id;
    }

    public List<Employee> findEmloyeeByName(String name) {
        List<Employee> employeeList = repository.findEmployeeByName(name);
        if(employeeList != null) return employeeList;
        else return repository.findAll();
    }

    public List<Employee> findEmployeeBetweenAge(Integer minAge, Integer maxAge) {
        List<Employee> employeeList = repository.findEmployeeByBetweenAge(minAge,maxAge);
        if(employeeList != null) return employeeList;
        else return repository.findAll();
    }
}
