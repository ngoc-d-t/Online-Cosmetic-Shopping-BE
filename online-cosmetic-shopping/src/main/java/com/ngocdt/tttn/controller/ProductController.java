package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.BestSellingProductDTO;
import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/public/products/latest")
    public ResponseEntity<List<ProductDTO>> showAll(
            @RequestParam(value = "categoryID", required = false, defaultValue = "0") Integer id,
            @RequestParam(value = "value", required = false, defaultValue = "") String value ) {
        if (id == 0 && !value.isEmpty())
            return ResponseEntity.ok().body(productService.showByName(value));
        else if (id == 0 && value.isEmpty())
            return ResponseEntity.ok().body(productService.showAll());
        else if (id > 0 && !value.isEmpty())
            return ResponseEntity.ok().body(productService.showByCategoryAndName(id, value));
        else if (id > 0 && value.isEmpty())
            return ResponseEntity.ok().body(productService.showByCategory(id));
        else
            throw new BadRequestException("No comment.");
    }
    @GetMapping("/public/products/bestselling")
    public ResponseEntity<List<BestSellingProductDTO>> showAll() {
        return ResponseEntity.ok().body(productService.showBestSellingProducts());
    }
    @GetMapping("/public/products/discount")
    public ResponseEntity<List<ProductDTO>> showAllDiscount() {
        return ResponseEntity.ok().body(productService.showDiscountProduct());
    }
    @GetMapping("/admin/products")
    public ResponseEntity<List<ProductDTO>> showAllAdmin() {
        return ResponseEntity.ok().body(productService.showAllAdmin());
    }
    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.showOne(id));
    }

    @PostMapping("/admin/products")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(productService.create(dto, request));
    }

    @PutMapping("/admin/products")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(productService.update(dto, request));
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}

