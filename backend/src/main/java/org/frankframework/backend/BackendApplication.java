package org.frankframework.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication app = configureApplication();
        app.run(args);
    }



    public static SpringApplication configureApplication() {
        return new SpringApplication(BackendApplication.class);
    }
}
