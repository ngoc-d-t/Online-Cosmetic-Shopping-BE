package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.dto.BrandDTO;
import com.ngocdt.tttn.security.service.UserDetailsImpl;
import com.ngocdt.tttn.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/brands")
public class BrandController {
    private final BrandService brandService;
    @GetMapping()
    public ResponseEntity<List<BrandDTO>> showAll() {
        return ResponseEntity.ok().body(brandService.showAll());
    }
}
