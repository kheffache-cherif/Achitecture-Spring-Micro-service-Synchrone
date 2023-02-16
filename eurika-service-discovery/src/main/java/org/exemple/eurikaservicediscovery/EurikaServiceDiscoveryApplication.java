package org.exemple.eurikaservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurikaServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurikaServiceDiscoveryApplication.class, args);
    }

}
