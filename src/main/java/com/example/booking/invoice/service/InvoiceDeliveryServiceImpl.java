package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.InvoiceDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvoiceDeliveryServiceImpl implements InvoiceDeliveryService {

    private final RestTemplate restTemplate;

    public InvoiceDeliveryServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    @CircuitBreaker(name = "delivery-service", fallbackMethod = "fallbackResponse")
    public String processInvoice(final InvoiceDto invoiceDto) {

        String url = "http://localhost:8081/api/delivery/invoice"; // URL of the random response API
        HttpEntity<Object> request = new HttpEntity<>(invoiceDto);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

    public String fallbackResponse(Exception ex) {
        System.out.println(ex.getMessage());
        return "Failed to access Delivery service : " + ex.getMessage();
    }

}
