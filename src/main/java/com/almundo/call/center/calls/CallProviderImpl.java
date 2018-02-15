/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Call;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Slf4j
@Component
public class CallProviderImpl implements CallProvider {
    private Long counter = 1L;
    
    @Autowired
    private Dispatcher dispatcher;
    
    
    @Override
    public void call() {
        log.info("Generando nueva llamada...");
        
        Call call = new Call();
        call.setId(counter++);
        call.setDuration(new Random().nextInt(5) + 5);
        
        dispatcher.dispacthCall(call);
    }
    
}
