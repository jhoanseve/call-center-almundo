/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

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
@RunWith(SpringRunner.class)
@SpringBootTest
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
        IntStream.range(0, 10).forEach(i -> callProvider.call());
        
        Thread.sleep(20000);
    }
    
}
