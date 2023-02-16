package com.example.billingservice.mappers;

import com.example.billingservice.DTO.InvoiceRequestDto;

import com.example.billingservice.DTO.InvoiceResponseDto;
import com.example.billingservice.entiries.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTOToInvioce(InvoiceRequestDto invoiceRequestDto);
    InvoiceResponseDto fromInvoiceToInvoiceResponseDto (Invoice invoice);

}
