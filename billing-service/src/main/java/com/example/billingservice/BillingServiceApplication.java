package com.example.billingservice;


import com.example.billingservice.DTO.InvoiceRequestDto;
import com.example.billingservice.DTO.InvoiceResponseDto;
import com.example.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients // pour l'activation de open Feign  <<CustomerRestClient>>
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            InvoiceResponseDto save =
                    invoiceService.save(new InvoiceRequestDto(new BigDecimal(8700), "001"));
                    invoiceService.save(new InvoiceRequestDto(new BigDecimal(5400),"001"));
                    invoiceService.save(new InvoiceRequestDto(new BigDecimal(11840),"002"));
        };
    }

}
//http://localhost:8083/h2-console