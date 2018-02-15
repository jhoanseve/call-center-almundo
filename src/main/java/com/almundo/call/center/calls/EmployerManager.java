/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Employee;
import java.util.Optional;

/**
 *
 * @author root
 */
public interface EmployerManager {
    
    /**
     * Metodo encargado de localizar y retornar un empleado disponible 
     * para atender llamadas
     * 
     * @return null si no encuentra un Empleado disponible
     */
    Optional<Employee> findAvailable();
}
