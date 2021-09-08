package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.SizeDTO;
import com.ngocdt.tttn.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/sizes")
public class SizeController {
    private final SizeService sizeService;
    @GetMapping
    public ResponseEntity<List<SizeDTO>> showAll(){
        return ResponseEntity.ok().body(sizeService.showAll());
    }
}
