package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.dto.InvoiceResponseDto;

public interface InvoiceService {

    public static String STATUS_SUCCESS = "Success";
    public static String STATUS_FAILED = "Failed";
    public static String SUCCESS_MESSAGE = "Invoice saved successfully";

    InvoiceResponseDto saveInvoice(InvoiceDto invoiceDto);
}
