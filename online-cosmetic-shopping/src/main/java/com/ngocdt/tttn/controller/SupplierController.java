package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.SupplierDTO;
import com.ngocdt.tttn.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping()
    public ResponseEntity<List<SupplierDTO>> showAll() {
        return ResponseEntity.ok().body(supplierService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(supplierService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<SupplierDTO> create(@Valid @RequestBody SupplierDTO dto) {
        return ResponseEntity.ok().body(supplierService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<SupplierDTO> update(@Valid @RequestBody SupplierDTO dto) {
        return ResponseEntity.ok().body(supplierService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        supplierService.delete(id);
        return ResponseEntity.ok().build();
    }
}

