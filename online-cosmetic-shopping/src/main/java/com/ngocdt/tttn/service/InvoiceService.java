package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.InvoiceDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> showAll();
    InvoiceDTO showOne(Integer id);
    InvoiceDTO create(InvoiceDTO dto, HttpServletRequest request);
    void delete(Integer id);
}
