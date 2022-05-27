package com.example.javaeefirst.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class AvailableClientsGenerator implements Serializable {
    public Integer generateAvailNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedAvailNumber = new Random().nextInt(300);
        return generatedAvailNumber;
    }
}
