package com.project.tel_book.configuration;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasbinConfig {

    @Bean
    public Enforcer casbinEnforcer() {
        Enforcer enforcer = new Enforcer("C:\\Users\\Baimyrza\\IdeaProjects\\tel_book\\src\\main\\resources\\casbin\\model.conf", "C:\\Users\\Baimyrza\\IdeaProjects\\tel_book\\src\\main\\resources\\casbin\\policy.csv");
        return enforcer;
    }
}