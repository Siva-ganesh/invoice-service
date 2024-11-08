package com.example.booking.invoice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Builder
@ToString(exclude = "invoice")
public class BillingLineInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "line_type")
    private String lineType;

    @Column(name = "resource_code")
    private String resourceCode;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;
}
