package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BillingLineDto {

    @JsonProperty("billing_line_information")
    private BillingLineInformationDto billingLineInformation;
}