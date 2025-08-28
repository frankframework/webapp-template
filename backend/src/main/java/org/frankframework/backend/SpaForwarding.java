package org.frankframework.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpaForwarding implements WebMvcConfigurer {
    @Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
        registry.addViewController("/frontend").setViewName("redirect:/frontend/index.html");
        registry.addViewController("/frontend/").setViewName("redirect:/frontend/index.html");
    }
}
