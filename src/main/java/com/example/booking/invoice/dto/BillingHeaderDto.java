package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BillingHeaderDto {

    @JsonProperty("billing_id")
    private String billingId;

    @JsonProperty("company_id")
    private int companyId;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("total_invoice_lines")
    private int totalInvoiceLines;

    @JsonProperty("total_invoice_amount")
    private int totalInvoiceAmount;
}
