package com.schedule.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

/*
- fixedRate: Ejecuta a intervalos fijos desde el inicio de cada ejecucion.
- fixedDelay: Ejecuta a intervalos fijos desde el final de cada ejecucion.
- initialDelay: Retrasa la primera ejecucion.
- cron: Ejecuta segun una expresion cron.
- zone: Especifica la zona horaria para una expresion cron
 */

@Component
public class SchedulTask {

    @Scheduled(cron = "0 0 15 * * 1,3,5", zone = "America/Bogota")
    public void scheduleMessage() throws InterruptedException {
        System.out.println("Hola padre...");
    }

    // ara ver las zonas horarias con las que trabaja Java
    /*
    public static void main(String[] args) {
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }
     */
}
