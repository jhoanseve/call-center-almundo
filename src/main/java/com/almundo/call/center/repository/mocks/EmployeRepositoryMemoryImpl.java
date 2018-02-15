/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls.mocks;

import com.almundo.call.center.model.Employee;
import static com.almundo.call.center.model.Employee.TYPE.*;
import com.almundo.call.center.repository.EmployeRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component
public class EmployeRepositoryMemoryImpl implements EmployeRepository {

    @Value("${mocks.employes.operators:6}")
    private int operatorsSize;
    @Value("${mocks.employes.supervisors:3}")
    private int supervisorsSize;
    @Value("${mocks.employes.directors:1}")
    private int directorsSize;
    
    private List<Employee> operators;
    private List<Employee> supervisors;
    private List<Employee> directors;
    
    @PostConstruct
    private void init() {
        operators = createCollection(operatorsSize, OPERADOR);
        supervisors = createCollection(supervisorsSize, SUPERVISOR);
        directors = createCollection(directorsSize, DIRECTOR);
    }

    
    @Override
    public List<Employee> findByType(Employee.TYPE type) {
        switch(type) {
            case OPERADOR:
                return operators;
                
            case SUPERVISOR:
                return supervisors;
                
            case DIRECTOR:
                return directors;
                
        }
        
        return null;
    }

    
    private List<Employee> createCollection(int size, Employee.TYPE type) {
        return IntStream.range(0, size)
                .mapToObj(index -> buildEmploye(index, type))
                .collect(Collectors.toList());
    }
    
    
    private Employee buildEmploye(int i, Employee.TYPE type) {
        Employee employe = new Employee();
        employe.setAvailable(true);
        employe.setName("emp-" + i);
        employe.setType(type);
        return employe;
    }
    
}
