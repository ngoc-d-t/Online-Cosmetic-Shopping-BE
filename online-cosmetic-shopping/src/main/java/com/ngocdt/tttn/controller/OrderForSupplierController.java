package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.OrderForSupplierDTO;
import com.ngocdt.tttn.service.OrderForSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/orderForSuppliers")
public class OrderForSupplierController {
    private final OrderForSupplierService orderForSupplierService;

    @GetMapping()
    public ResponseEntity<List<OrderForSupplierDTO>> showAll() {
        return ResponseEntity.ok().body(orderForSupplierService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderForSupplierDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(orderForSupplierService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<OrderForSupplierDTO> create(@Valid @RequestBody OrderForSupplierDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(orderForSupplierService.create(dto, request));
    }

    @PutMapping()
    public ResponseEntity<OrderForSupplierDTO> update(@Valid @RequestBody OrderForSupplierDTO dto) {
        return ResponseEntity.ok().body(orderForSupplierService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        orderForSupplierService.delete(id);
        return ResponseEntity.ok().build();
    }

}
