package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.InvoiceDTO;
import com.ngocdt.tttn.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/admin/invoices")
@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping()
    public ResponseEntity<List<InvoiceDTO>> showAll(){
        return ResponseEntity.ok().body(invoiceService.showAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(invoiceService.showOne(id));
    }
    @PostMapping()
    public ResponseEntity<InvoiceDTO> create(@Valid  @RequestBody InvoiceDTO dto, HttpServletRequest request){
        return ResponseEntity.ok().body(invoiceService.create(dto, request));
    }
    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        invoiceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
