package io.github.khanhdpdx01.veserver;

import io.github.khanhdpdx01.veserver.identity.EnrollAdmin;
import io.github.khanhdpdx01.veserver.identity.RegisterUser;
import io.github.khanhdpdx01.veserver.service.DiplomaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeServerApplication {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
            DiplomaService.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(VeServerApplication.class, args);
    }
}
