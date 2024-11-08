package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class InvoiceDto {

    @JsonProperty("billing_header")
    private BillingHeaderDto billingHeader;
    @JsonProperty("billing_lines")
    private ArrayList<BillingLineDto> billingLines;
}
