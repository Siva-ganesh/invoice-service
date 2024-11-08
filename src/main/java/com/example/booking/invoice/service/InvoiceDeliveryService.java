package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.InvoiceDto;

public interface InvoiceDeliveryService
{
    String processInvoice(InvoiceDto invoiceDto);
}
