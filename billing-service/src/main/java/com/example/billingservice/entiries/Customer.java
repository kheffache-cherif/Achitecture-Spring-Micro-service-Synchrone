package com.example.billingservice.entiries;

import lombok.Data;

// ce n'est pas une entité Jpa.
@Data
public class Customer {
    private  String id;
    private String name;
    private String email;
}
