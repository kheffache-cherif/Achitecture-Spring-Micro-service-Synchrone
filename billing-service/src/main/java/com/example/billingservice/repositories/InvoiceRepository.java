package com.example.billingservice.repositories;

import com.example.billingservice.entiries.Invoice;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice , String> {


    List<Invoice> findByCustomerID(String customerId);

    List<Invoice> findAllBy();
}
