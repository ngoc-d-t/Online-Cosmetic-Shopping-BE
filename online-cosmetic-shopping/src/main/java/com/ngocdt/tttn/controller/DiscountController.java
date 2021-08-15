package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.DiscountDTO;
import com.ngocdt.tttn.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping()
    public ResponseEntity<List<DiscountDTO>> showAll() {
        return ResponseEntity.ok().body(discountService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(discountService.showOne(id));
    }

    @PutMapping()
    public ResponseEntity<DiscountDTO> update(@Valid @RequestBody DiscountDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(discountService.update(dto, request));
    }

    @PostMapping()
    public ResponseEntity<DiscountDTO> create(@Valid @RequestBody DiscountDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(discountService.create(dto, request));
    }

    @DeleteMapping()
    public ResponseEntity<DiscountDTO> delete(@RequestParam("id") Integer id) {
        discountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
