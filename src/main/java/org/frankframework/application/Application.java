package org.frankframework.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = configureApplication();
        app.run(args);
    }



    public static SpringApplication configureApplication() {
        return new SpringApplication(Application.class);
    }
}
