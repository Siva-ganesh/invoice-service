package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.dto.InvoiceResponseDto;

public interface InvoiceService {

    InvoiceResponseDto saveInvoice(InvoiceDto invoiceDto);
}
