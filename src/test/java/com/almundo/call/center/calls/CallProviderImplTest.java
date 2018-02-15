/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author root
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CallProviderImplTest {
    @Autowired
    private CallProvider callProvider;
    
    public CallProviderImplTest() {
    }
    
    
    /**
     * Test of call method, of class CallProviderImpl.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testCall() throws InterruptedException {
        int calls = 30;
        IntStream.range(0, calls).forEach(i -> callProvider.call());
        
        while(CallProcessor.counter.get() < calls) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
    
}
