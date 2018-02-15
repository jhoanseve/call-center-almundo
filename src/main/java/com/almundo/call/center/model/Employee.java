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

    /**
     * Metodo encargado de bloquear al empleado para que no pueda ser usado 
     * en mas de una llamada al tiempo
     */
    public void hold() {
        this.available = Boolean.FALSE;
    }
}
