package com.example.booking.invoice.controller;

import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.dto.InvoiceResponseDto;
import com.example.booking.invoice.entity.Invoice;
import com.example.booking.invoice.service.InvoiceDeliveryServiceImpl;
import com.example.booking.invoice.service.InvoiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;
    private final InvoiceDeliveryServiceImpl invoiceDeliveryService;

    public InvoiceController(
            final InvoiceServiceImpl invoiceService,
            final InvoiceDeliveryServiceImpl invoiceDeliveryService) {
        this.invoiceService = invoiceService;
        this.invoiceDeliveryService = invoiceDeliveryService;
    }

    @PostMapping(value = "/save", consumes="application/json", produces="application/json")
    public ResponseEntity<InvoiceResponseDto> saveInvoice(@RequestBody InvoiceDto invoiceDto) {

//        System.out.println(invoiceDto.toString());
        InvoiceResponseDto invoiceResponse = invoiceService.saveInvoice(invoiceDto);
        String deliveryResponse = invoiceDeliveryService.processInvoice(invoiceDto);
        invoiceResponse.setDeliveryStatus(deliveryResponse);
        System.out.println(invoiceResponse);
        return ResponseEntity.ok(invoiceResponse);
    }
}
