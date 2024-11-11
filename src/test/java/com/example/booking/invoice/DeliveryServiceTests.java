package com.example.booking.invoice;

import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.service.InvoiceDeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@SpringBootTest
class DeliveryServiceTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InvoiceDeliveryServiceImpl invoiceDeliveryService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void processInvoice_SuccessfulResponse_Test() {

        InvoiceDto invoiceDto = new InvoiceDto();
        String expectedResponse = "Success";

        mockServer.expect(requestTo("http://localhost:8081/api/delivery/invoice"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(expectedResponse));

        String actualResponse = invoiceDeliveryService.processInvoice(invoiceDto);
        assertEquals(expectedResponse, actualResponse);
        mockServer.verify();
    }

    @Test
    void processInvoice_FailedResponse_Test() {

        InvoiceDto invoiceDto = new InvoiceDto();
        String expectedResponse = "Failed to access Delivery service : 500 Internal Server Error: \"Internal Server Error\"";

        mockServer.expect(requestTo("http://localhost:8081/api/delivery/invoice"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("Internal Server Error"));

        String actualResponse = invoiceDeliveryService.processInvoice(invoiceDto);
        assertEquals(expectedResponse, actualResponse);
        mockServer.verify();
    }
}
