package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.BrandDTO;
import com.ngocdt.tttn.dto.CharacteristicDTO;
import com.ngocdt.tttn.service.BrandService;
import com.ngocdt.tttn.service.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/characteristics")
public class CharacteristicController {
    private final CharacteristicService characteristicService;
    @GetMapping()
    public ResponseEntity<List<CharacteristicDTO>> showAll() {
        return ResponseEntity.ok().body(characteristicService.showAll());
    }
}
