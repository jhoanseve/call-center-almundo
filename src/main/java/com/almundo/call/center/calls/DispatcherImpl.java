/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.call.center.calls;

import com.almundo.call.center.model.Call;
import com.almundo.call.center.model.Employee;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de procesar las llamadas entrantes
 * 
 * Esta clase implementa una cola interna para las llamadas, esto con el fin de 
 * que el sistema pueda controlar y poner en espera dichas llamadas cuando no hayan
 * empleados disponibles para atenderlas
 * 
 * @author root
 */
@Slf4j
@Component
public class DispatcherImpl implements Dispatcher, Runnable {

    /**
     * Cola de procesamiento interno es una instancia de ConcurrentLinkedDeque
     * para evitar problemas de sincronizacion debido a que diferentes Threads 
     * pueden accederla
     */
    private final Queue<Call> internalQueue = new ConcurrentLinkedDeque<>();
    
    /**
     * Ejecutor de proceso que se encarga de realizar pooling a la cola interna
     * para iniciar el procesamiento de las llamadas
     */
    private final ScheduledExecutorService executorSchedule = Executors
            .newSingleThreadScheduledExecutor(new CustomizableThreadFactory("PoolQueueEx-"));
    
    /**
     * Executor de los Threads que procesan las llamadas
     */
    private ExecutorService executorCalls;

    /**
     * Define el numero maximo de llamadas que puede atender el sistema de manera 
     * concurrente
     */
    @Min(1)
    @Value("${dispatcher.max.calls:10}")
    private int maxCalls;

    /**
     * Mensaje personalizado en el archivo de configuracion que se le enviara 
     * para las llamadas en espera
     */
    @Value("${dispatcher.wait.message}")
    private String waitMessage;

    /**
     * Proveedor de los empleados
     */
    @Autowired
    private EmployerManager employerManager;

    
    @PostConstruct
    private void init() {
        /**
         * Se inicia el executor service con parametro de maximo numero de
         * llamadas que puede atender
         */
        executorCalls = Executors.newFixedThreadPool(maxCalls,
                new CustomizableThreadFactory("CallExecutor-"));

        /**
         * Se inicia tarea programada que va a realizar el pooling de la cola
         * de llamadas internas
         */
        executorSchedule.scheduleAtFixedRate(this, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void dispacthCall(Call call) {
        Objects.requireNonNull(call, "Llamada nula!");
        
        // Se agrega la nueva llamada a la cola de procesamiento
        internalQueue.add(call);
    }

    
    @Override
    public void run() {
        log.debug("-- Pooling internal queue..");

        // Se verifica si hay llamadas en cola
        if (!this.internalQueue.isEmpty()) {
            // Se busca un empleado disponible 
            Optional<Employee> employee = employerManager.findAvailable();
            if (employee.isPresent()) {
                // Se obtiene la proxima llamada a atender
                Call nextCall = this.internalQueue.poll();
                if (nextCall != null) {
                    // se asigna el empleado que va a atender la llamada
                    nextCall.assignmentEmployee(employee.get());
                    executorCalls.submit(new CallProcessor(nextCall));
                }

            } else {
                //send wait message to call
                log.warn("-- No hay empleados disponibles para atender las llamadas!");
                internalQueue.forEach(call -> call.sendMessage(waitMessage));
            }
        } else {
            log.debug("No hay llamadas pendientes por procesar");
        }
    }
}
