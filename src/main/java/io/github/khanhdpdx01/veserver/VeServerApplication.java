package io.github.khanhdpdx01.veserver;

import io.github.khanhdpdx01.veserver.identity.EnrollAdmin;
import io.github.khanhdpdx01.veserver.identity.RegisterUser;
import io.github.khanhdpdx01.veserver.service.DiplomaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class VeServerApplication {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");

        try {
            Properties configuration = new Properties();
            InputStream inputStream = VeServerApplication.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            configuration.load(inputStream);
            inputStream.close();
            String property = configuration.getProperty("fabric.pem-file.location");

            EnrollAdmin.main(null);
            RegisterUser.main(null);
            DiplomaService.connect(property);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(VeServerApplication.class, args);
    }
}
