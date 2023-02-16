package com.example.billingservice.services;


import com.example.billingservice.DTO.InvoiceRequestDto;
import com.example.billingservice.DTO.InvoiceResponseDto;
import com.example.billingservice.entiries.Customer;
import com.example.billingservice.entiries.Invoice;
import com.example.billingservice.exceptions.CustomerNotFoundException;
import com.example.billingservice.mappers.InvoiceMapper;
import com.example.billingservice.openFeignClientRest.CustomerRestClient;
import com.example.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto) {
        /*
        *
         Micro service = verification de la regle metier
         Vérification de l'intégralitéréferentiel Invoice/customer
        * */
        Customer MonCustomer;
        try {
            MonCustomer = customerRestClient.getCustomer(invoiceRequestDto.getCustomerID());

        } catch (Exception e){
            throw new CustomerNotFoundException("custumer not found !!!!!!!!!!!!!!!!!!");
        }
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTOToInvioce(invoiceRequestDto);
         invoice.setId(UUID.randomUUID().toString());
         invoice.setDate(new Date());
        Invoice SavedInvoice = invoiceRepository.save(invoice);
        SavedInvoice.setCustomer(MonCustomer);

        return  invoiceMapper.fromInvoiceToInvoiceResponseDto(SavedInvoice);
    }

    @Override
    public InvoiceResponseDto getInvoice(String invoceId) {
        // Je cherche la facture à partir de la base de donnes du micro service
        Invoice invoice = invoiceRepository.findById(invoceId).orElse(null);

        if(invoice==null) throw new RuntimeException("Invoice Not found");
        //Recuperation des donnees du customer avec le service open Feign et son idCustomer dans invoice.dans une autre BD
        Customer customer1 = customerRestClient.getCustomer(invoice.getCustomerID());
        //J'integre mes nouvelles donnes dans le custumer de la facture
        invoice.setCustomer(customer1);

        return invoiceMapper.fromInvoiceToInvoiceResponseDto(invoice);
    }

    @Override
    public List<InvoiceResponseDto> invoicesByCustomerId(String customerId) {
       List <Invoice> invoices = invoiceRepository.findByCustomerID(customerId);
        for (Invoice inv: invoices) {
            //je recupere in customer à partir d'un micro-service
            Customer customer1 = customerRestClient.getCustomer(inv.getCustomerID());
            //je l'ajoute à une facture
            inv.setCustomer(customer1);
        }
        return invoices.
                stream()
                .map(invoice ->invoiceMapper.fromInvoiceToInvoiceResponseDto(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDto> listInvoice() {
        List<Invoice> invoices = invoiceRepository.findAll();
       for (Invoice inv: invoices) {
          //je recupere in customer à partir d'un micro-service
               Customer customer1 = customerRestClient.getCustomer(inv.getCustomerID());
               //je l'ajoute à une facture
               inv.setCustomer(customer1);
       }
        //comme c'set une liste donc je dois recupéré chaque elment et le convertir et en suite tous collecté dans une liste

        return  invoices.stream()
                .map(invoice -> invoiceMapper.fromInvoiceToInvoiceResponseDto(invoice))
                .collect(Collectors.toList());
    }
}
