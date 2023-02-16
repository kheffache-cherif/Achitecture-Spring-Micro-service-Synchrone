package com.example.billingservice.DTO;

import com.example.billingservice.entiries.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponseDto {
    private String id;
    private Date date;
    private BigDecimal amount;
    private Customer customer;

}