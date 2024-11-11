package com.example.booking.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class BillingLineInformationDto {

    @JsonProperty("action_type")
    private String actionType;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("line_type")
    private String lineType;

    @JsonProperty("resource_code")
    private String resourceCode;

}
