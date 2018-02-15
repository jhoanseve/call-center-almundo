/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Call;

/**
 *
 * @author root
 */
public interface Dispatcher {
    
    /**
     * Metodo encargado de enrrutar una llamada hacia un empleado disponible
     * 
     * @param call 
     */
    void dispacthCall(Call call);
}
