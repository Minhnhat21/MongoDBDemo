package com.minhnhat.demo.employeemanager.controller;

import com.minhnhat.demo.employeemanager.model.Employee;
import com.minhnhat.demo.employeemanager.sevice.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> employees = new ArrayList<>();
        if (employeeService.getAllEmployee() == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            employeeService.getAllEmployee().forEach(employees::add);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") String id) {
        Optional<Employee> employeeData = employeeService.getEmployeeById(id);
        if (employeeData.isPresent())
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        try {
            Employee _employee = employeeService.createNewEmployee(employee);
            return new ResponseEntity<>(_employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        try {
            Employee _employee = employeeService.updateEmployee(id, employee);
            return new ResponseEntity<>(_employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            return new ResponseEntity<>(employeeService.deleteOne(id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Find Employee by name
    @GetMapping("/employees/search")
    public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestParam(name = "name") String name) {
        List<Employee> listEmp = new ArrayList<>();
        if (employeeService.findEmloyeeByName(name) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            employeeService.findEmloyeeByName(name).forEach(listEmp::add);
        }
        return new ResponseEntity<>(listEmp, HttpStatus.OK);
    }

    @GetMapping("/employees/search/age")
    public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestParam(name = "minAge") Integer minAge,@RequestParam(name = "maxAge") Integer maxAge) {
        List<Employee> listEmp = new ArrayList<>();
        if (employeeService.findEmployeeBetweenAge(minAge,maxAge) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            employeeService.findEmployeeBetweenAge(minAge,maxAge).forEach(listEmp::add);
        }
        return new ResponseEntity<>(listEmp, HttpStatus.OK);
    }
}
