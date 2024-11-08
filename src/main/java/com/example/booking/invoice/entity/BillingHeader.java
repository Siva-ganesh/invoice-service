package com.example.booking.invoice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class BillingHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "billing_id")
    private String billingId;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "total_invoice_lines")
    private int totalInvoiceLines;

    @Column(name = "total_invoice_amount")
    private int totalInvoiceAmount;
}
