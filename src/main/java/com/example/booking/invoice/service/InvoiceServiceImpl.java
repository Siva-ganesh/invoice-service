package com.example.booking.invoice.service;

import com.example.booking.invoice.dto.BillingHeaderDto;
import com.example.booking.invoice.dto.BillingLineInformationDto;
import com.example.booking.invoice.dto.InvoiceDto;
import com.example.booking.invoice.dto.InvoiceResponseDto;
import com.example.booking.invoice.entity.BillingHeader;
import com.example.booking.invoice.entity.BillingLineInformation;
import com.example.booking.invoice.entity.Invoice;
import com.example.booking.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private static final String STATUS_SUCCESS = "Success";
    private static final String SUCCESS_MESSAGE = "Invoice saved successfully";


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

            List<BillingLineInformation> billingLineInformations = invoiceDto.getBillingLines()
                    .stream()
                    .map(billingLineDto -> getBillingInformation(billingLineDto.getBillingLineInformation(), invoice))
                    .toList();
            invoice.setBillingLineInformations(billingLineInformations);

            invoiceRepository.save(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getInvoiceResponseDto(invoice);
    }

    private InvoiceResponseDto getInvoiceResponseDto(Invoice invoice) {

        return InvoiceResponseDto.builder()
                .invoiceId(invoice.getId())
                .invoiceStatus(STATUS_SUCCESS)
                .message(SUCCESS_MESSAGE)
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
