package org.example.userauthenticationservice_sept2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserAuthenticationServiceSept2024Application {

    public static void main(String[] args) {
        SpringApplication.run(UserAuthenticationServiceSept2024Application.class, args);
    }

}
