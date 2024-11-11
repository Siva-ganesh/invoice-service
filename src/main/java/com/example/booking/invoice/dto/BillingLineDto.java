package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class BillingLineDto {

    @JsonProperty("billing_line_information")
    private BillingLineInformationDto billingLineInformation;
}
