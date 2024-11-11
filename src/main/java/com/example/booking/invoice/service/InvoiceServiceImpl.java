package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.BillingHeaderDto;
import com.example.booking.invoice.dto.BillingLineInformationDto;
import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.dto.InvoiceResponseDto;
import com.example.booking.invoice.entity.BillingHeader;
import com.example.booking.invoice.entity.BillingLineInformation;
import com.example.booking.invoice.entity.Invoice;
import com.example.booking.invoice.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceResponseDto saveInvoice(InvoiceDto invoiceDto) {

        final Invoice invoice = new Invoice();
        try {
            BillingHeaderDto billingHeaderDto = invoiceDto.getBillingHeader();
            BillingHeader billingHeader = BillingHeader.builder()
                    .billingId(billingHeaderDto.getBillingId())
                    .companyId(billingHeaderDto.getCompanyId())
                    .currencyCode(billingHeaderDto.getCurrencyCode())
                    .totalInvoiceAmount(billingHeaderDto.getTotalInvoiceAmount())
                    .totalInvoiceLines(billingHeaderDto.getTotalInvoiceLines())
                    .build();
            invoice.setBillingHeader(billingHeader);

            List<BillingLineInformation> billingLineInformations = Optional.ofNullable(invoiceDto.getBillingLines()).orElse(Collections.emptyList())
                    .stream()
                    .map(billingLineDto -> getBillingInformation(billingLineDto.getBillingLineInformation(), invoice))
                    .toList();
            invoice.setBillingLineInformations(billingLineInformations);

            Invoice savedInvoice = invoiceRepository.save(invoice);
            return getInvoiceResponseDto(savedInvoice);
        } catch (Exception e) {
            logger.error("Exception : {}", e.getMessage());
            return getFailedInvoiceResponseDto(e.getMessage());
        }
    }

    private InvoiceResponseDto getInvoiceResponseDto(Invoice invoice) {

        return InvoiceResponseDto.builder()
                .invoiceId(invoice.getId())
                .invoiceStatus(STATUS_SUCCESS)
                .message(SUCCESS_MESSAGE)
                .build();
    }

    private InvoiceResponseDto getFailedInvoiceResponseDto(String errorMessage) {

        return InvoiceResponseDto.builder()
                .invoiceStatus(STATUS_FAILED)
                .message(errorMessage)
                .build();
    }

    private BillingLineInformation getBillingInformation(
            final BillingLineInformationDto billingLineInformationDto,
            final Invoice invoice) {

        return BillingLineInformation.builder()
                .invoice(invoice)
                .lineType(billingLineInformationDto.getLineType())
                .actionType(billingLineInformationDto.getActionType())
                .productType(billingLineInformationDto.getProductType())
                .resourceCode(billingLineInformationDto.getResourceCode())
                .build();
    }
}
