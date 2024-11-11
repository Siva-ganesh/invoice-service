package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class InvoiceDto {

    @JsonProperty("billing_header")
    private BillingHeaderDto billingHeader;
    @JsonProperty("billing_lines")
    private List<BillingLineDto> billingLines;
}
