package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.ToneDTO;
import com.ngocdt.tttn.service.ToneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/tones")
public class ToneController {
    private final ToneService toneService;
    @GetMapping
    public ResponseEntity<List<ToneDTO>> showAll(){
        return ResponseEntity.ok().body(toneService.showAll());
    }
}
