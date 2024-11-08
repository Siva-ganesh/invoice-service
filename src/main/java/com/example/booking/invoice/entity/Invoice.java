package com.example.booking.invoice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "billing_header_id", referencedColumnName = "id")
    private BillingHeader billingHeader;

    @OneToMany(mappedBy = "invoice" , cascade = CascadeType.ALL)
    private List<BillingLineInformation> billingLineInformations;

}
