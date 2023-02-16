package com.example.billingservice.web;

import com.example.billingservice.DTO.InvoiceRequestDto;
import com.example.billingservice.DTO.InvoiceResponseDto;
import com.example.billingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {

    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDto> allInvoices(){
        return invoiceService.listInvoice();
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDto save(@RequestBody InvoiceRequestDto invoceRequestDto) {
        return invoiceService.save(invoceRequestDto);
    }
    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDto getInvoice(@PathVariable(name = "id") String invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoicesByCustomer/{customerId}")

    public List<InvoiceResponseDto> getInvoicesByCustomerId(@PathVariable  String customerId) {
        return invoiceService.invoicesByCustomerId(customerId);
    }

    //pour recuperer et afficher les messages des exceptions
    @ExceptionHandler(Exception.class)
     public ResponseEntity<String> expentionHandler(Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
