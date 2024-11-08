package com.example.booking.invoice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class InvoiceResponseDto {

    private String invoiceStatus;
    private int invoiceId;
    private String message;
    private String deliveryStatus;
}
