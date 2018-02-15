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
public class Call {
    private Long id;
    private int duration;
    private Employee employee;

    public void sendMessage(String waitMessage) {
        System.out.println(waitMessage);
    }

    /**
     * Metodo encargado de asignar y bloquear el usuario
     * 
     * @param employee
     */
    public void assignmentEmployee(Employee employee) {
        employee.hold();
        this.employee = employee;
    }
}
