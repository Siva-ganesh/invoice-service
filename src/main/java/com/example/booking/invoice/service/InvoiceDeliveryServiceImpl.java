package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.InvoiceDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvoiceDeliveryServiceImpl implements InvoiceDeliveryService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceDeliveryServiceImpl.class);

    public InvoiceDeliveryServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "delivery-service", fallbackMethod = "fallbackResponse")
    public String processInvoice(final InvoiceDto invoiceDto) {

        String url = "http://localhost:8081/api/delivery/invoice"; // URL of the random response API
        HttpEntity<Object> request = new HttpEntity<>(invoiceDto);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

    private String fallbackResponse(Exception ex) {
        logger.error(ex.getMessage());
        return "Failed to access Delivery service : " + ex.getMessage();
    }
}
