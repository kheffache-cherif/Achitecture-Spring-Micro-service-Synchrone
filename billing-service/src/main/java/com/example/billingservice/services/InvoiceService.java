package com.example.billingservice.services;



import com.example.billingservice.DTO.InvoiceRequestDto;
import com.example.billingservice.DTO.InvoiceResponseDto;

import java.util.List;


public interface InvoiceService {
    public InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto);

    public InvoiceResponseDto getInvoice (String id);
    List<InvoiceResponseDto> invoicesByCustomerId(String customerId);

    List<InvoiceResponseDto> listInvoice();


}
