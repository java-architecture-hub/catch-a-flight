package jah.catchflight.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Spring Boot application.
 * This class serves as the entry point for the application, initializing
 * the Spring application context and starting the embedded server.
 */
@SpringBootApplication
public class Main {
    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
