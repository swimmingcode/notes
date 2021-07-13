package com.cico.virmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author youjiancheng
 */
@EnableScheduling
@SpringBootApplication
public class VirmachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirmachineApplication.class, args);
    }

}
