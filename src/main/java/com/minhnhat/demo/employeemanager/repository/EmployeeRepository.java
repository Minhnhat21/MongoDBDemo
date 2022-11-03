package com.minhnhat.demo.employeemanager.repository;

import com.minhnhat.demo.employeemanager.model.Employee;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    @Aggregation(pipeline = {
            "{'$match' : { '$or':  [\n" +
                    "    {\"firstName\" : {$regex: ?0, $options:'i'}},\n" +
                    "    {\"lastName\" : {$regex: ?0, $options:'i'}}\n" +
                    "  ]}}",
            "{'$sort': {'age':1}}" 

    })
    List<Employee> findEmployeeByName(String name);

    @Aggregation(pipeline = {
         "{'$match' : {'age' : { $gt: ?0, $lt: ?1 }}} \n",
         "{'$sort': {'age':1}}"
    })
    List<Employee> findEmployeeByBetweenAge(Integer minAge, Integer maxAge);

}
