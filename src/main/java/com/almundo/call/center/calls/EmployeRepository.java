/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Employee;
import java.util.List;

/**
 *
 * @author root
 */
public interface EmployeRepository {
    
    /**
     * Metodo encargado de consular empleado por el tipo
     * 
     * @param type
     * @return 
     */
    public List<Employee> findByType(Employee.TYPE type);
}
