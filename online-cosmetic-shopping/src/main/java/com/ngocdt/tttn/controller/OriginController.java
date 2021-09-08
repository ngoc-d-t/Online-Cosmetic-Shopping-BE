package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.OriginDTO;
import com.ngocdt.tttn.service.OriginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/origins")
public class OriginController {
    private final OriginService originService;
    @GetMapping()
    public ResponseEntity<List<OriginDTO>> showAll(){
        return ResponseEntity.ok().body(originService.showAll());
    }
}
