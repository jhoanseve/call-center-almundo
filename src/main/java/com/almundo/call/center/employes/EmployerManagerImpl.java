/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.employes;

import com.almundo.call.center.model.Employee;
import static com.almundo.call.center.model.Employee.TYPE.*;
import com.almundo.call.center.repository.EmployeRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de proveer los empleados que se encuentren disponibles
 * para procesar llamadas
 * 
 * @author root
 */
@Slf4j
@Component
public class EmployerManagerImpl implements EmployerManager {
    @Autowired
    private EmployeRepository employeRepository;
    
    
    @Override
    public Optional<Employee> findAvailable() {
        log.info("Buscando un Empleado disponible.");
        
        Optional<Employee> empl = find(employeRepository.findByType(OPERADOR));
        if(empl.isPresent()) {
            log.debug("Se encontro operador disponible");
            return empl;
        }
        
        empl = find(employeRepository.findByType(SUPERVISOR));
        if(empl.isPresent()) {
            log.debug("Se encontro supervisor disponible");
            return empl;
        }
        
        empl = find(employeRepository.findByType(DIRECTOR));
        return empl;
    }

    
    /**
     * Metodo encargado de localizar un empleado disponible
     * 
     * @param employes
     * @return 
     */
    private Optional<Employee> find(List<Employee> employes) {
        return employes.stream()
                .parallel()
                .filter(e -> e.isAvailable()).findAny();
    }
    
}
