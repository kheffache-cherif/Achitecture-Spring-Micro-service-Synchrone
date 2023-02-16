package com.example.billingservice.openFeignClientRest;

import com.example.billingservice.entiries.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
// @FeignClient vient de open qu'il faut activer avant utilisation
@FeignClient(name = "CUSTOMER-SERVICE") // spécifique au .prepreties de customer
public interface CustomerRestClient {
    @GetMapping(path ="/api/customers/{id}")
    Customer getCustomer(@PathVariable(name = "id") String id);

    @GetMapping(path = "/api/customers")
    List<Customer> getAllCustomer();
}
