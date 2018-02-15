/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.model;

import lombok.Data;

/**
 *
 * @author root
 */
@Data
public class Employee {
    public static enum TYPE {
        OPERADOR,
        SUPERVISOR,
        DIRECTOR
    }
    
    private String name;
    private TYPE type;
    private boolean available;

    public synchronized boolean isAvailable() {
        return this.available;
    }
    
    /**
     * Metodo encargado de bloquear al empleado para que no pueda ser usado 
     * en mas de una llamada al tiempo
     */
    protected synchronized void hold() {
        this.available = Boolean.FALSE;
    }

    /**
     * Metodo encargado de colocar disponible un empleado
     */
    protected synchronized void release() {
        this.available = Boolean.TRUE;
    }
}
