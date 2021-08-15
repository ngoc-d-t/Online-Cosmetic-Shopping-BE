package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.InvoiceDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Invoice;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.InvoiceRepository;
import com.ngocdt.tttn.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepo;
    private final AccountRepository accountRepo;
    @Override
    public List<InvoiceDTO> showAll() {
        return invoiceRepo.findAll().stream().map(InvoiceDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO showOne(Integer id) {
        return InvoiceDTO.toDTO(invoiceRepo.findById(id).orElse(null));
    }

    @Override
    public InvoiceDTO create(InvoiceDTO dto, HttpServletRequest request) {
        Account accountCurrent = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Invoice invoice = InvoiceDTO.toEntity(dto);
        invoice.setEmployee(accountCurrent.getEmployee());
        invoice.setInvoiceID(0);
        return InvoiceDTO.toDTO(invoiceRepo.save(invoice));
    }

    @Override
    public void delete(Integer id) {
        if(!invoiceRepo.existsById(id))
            throw new BadRequestException("Not found.");
        invoiceRepo.deleteById(id);
    }
}
