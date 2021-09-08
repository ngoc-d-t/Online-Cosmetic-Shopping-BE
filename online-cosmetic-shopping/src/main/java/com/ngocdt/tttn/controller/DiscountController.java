package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.DiscountDTO;
import com.ngocdt.tttn.dto.DiscountDetailDTO;
import com.ngocdt.tttn.entity.DiscountDetail;
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
    public ResponseEntity<DiscountDTO> update(@Valid @RequestBody DiscountDTO dto) {
        return ResponseEntity.ok().body(discountService.update(dto));
    }
    @PutMapping("/detail")
    public ResponseEntity<DiscountDetailDTO> updateDetail(@Valid @RequestBody DiscountDetailDTO dto){
        return ResponseEntity.ok().body(discountService.updateDetail(dto));
    }
    @PostMapping("/detail")
    public ResponseEntity<DiscountDetailDTO> createDetail(@Valid @RequestBody DiscountDetailDTO dto){
        return ResponseEntity.ok().body(discountService.createDetail(dto));
    }
    @PostMapping()
    public ResponseEntity<DiscountDTO> create(@Valid @RequestBody DiscountDTO dto) {
        return ResponseEntity.ok().body(discountService.create(dto));
    }

    @DeleteMapping()
    public ResponseEntity<DiscountDTO> delete(@RequestParam("id") Integer id) {
        discountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
