/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Call;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que representa una llamada
 *
 * @author root
 */
@Slf4j
public class CallProcessor implements Callable<Call> {

    private final Call call;

    public CallProcessor(Call call) {
        this.call = call;
    }


    @Override
    public Call call() throws Exception {
        log.info("Inicio de atencion de llamada [{}] por el empleado [{}]", 
                call.getId(), call.getEmployee());
        
        try {
            TimeUnit.SECONDS.sleep(call.getDuration());
        } catch (InterruptedException ex) {
        } finally {
            log.info("Finalizacion de llamada [{}]", call);
        }
        
        return call;
    }
}
