package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDTO>> showAll(
            @RequestParam(value = "categoryID", required = false, defaultValue = "0") Integer id) {
        if (id == 0)
            return ResponseEntity.ok().body(productService.showAll());
        else
            return ResponseEntity.ok().body(productService.showByCategory(id));
    }

    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.showOne(id));
    }

    @PostMapping("/admin/products")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(productService.create(dto, request));
    }

    @Secured("ADMIN")
    @PutMapping()
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok().body(productService.update(dto));
    }

    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}

