package com.example.booking.invoice;

import com.example.booking.invoice.dto.*;
import com.example.booking.invoice.entity.Invoice;
import com.example.booking.invoice.repository.InvoiceRepository;
import com.example.booking.invoice.service.InvoiceService;
import com.example.booking.invoice.service.InvoiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class InvoiceServiceTests {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    void saveInvoice_Success_Test() {

        BillingHeaderDto billingHeaderDto = new BillingHeaderDto()
                .setBillingId("AB456")
                .setCompanyId(1)
                .setCurrencyCode("INR")
                .setTotalInvoiceLines(1)
                .setTotalInvoiceAmount(1000);

        BillingLineDto billingLineDto = new BillingLineDto()
                .setBillingLineInformation(new BillingLineInformationDto()
                        .setLineType("PRODUCT")
                        .setProductType("CAR")
                        .setActionType("ISSUED")
                        .setResourceCode("LOC1"));
        List<BillingLineDto> billingLines = List.of(billingLineDto);
        InvoiceDto invoiceDto = new InvoiceDto()
                .setBillingHeader(billingHeaderDto)
                .setBillingLines(billingLines);

        Invoice savedInvoice = new Invoice();
        savedInvoice.setId(1);

        when(invoiceRepository.save(any(Invoice.class))).thenReturn(savedInvoice);
        InvoiceResponseDto invoiceResponseDto = invoiceService.saveInvoice(invoiceDto);

        assertEquals(InvoiceService.STATUS_SUCCESS, invoiceResponseDto.getInvoiceStatus());
        assertEquals(savedInvoice.getId(), invoiceResponseDto.getInvoiceId());
    }

    @Test
    void saveInvoice_Fail_Test() {

        InvoiceDto invoiceDto = new InvoiceDto()
                .setBillingHeader(null)
                .setBillingLines(null);
        InvoiceResponseDto invoiceResponseDto = invoiceService.saveInvoice(invoiceDto);
        assertEquals(InvoiceService.STATUS_FAILED, invoiceResponseDto.getInvoiceStatus());
    }
}
