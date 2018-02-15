/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls.web;

import com.almundo.call.center.calls.CallProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller donde seria el punto de entrada de las llamadas
 * 
 * @author root
 */
@RestController
@RequestMapping("/api")
public class CallWebController {
    
    @Autowired
    private CallProvider callProvider;
    
    
    @RequestMapping("/call")
    public void call() {
        callProvider.call();
    }
}
