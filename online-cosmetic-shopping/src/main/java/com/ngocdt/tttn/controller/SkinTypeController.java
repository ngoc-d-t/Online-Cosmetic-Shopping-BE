package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.SkinTypeDTO;
import com.ngocdt.tttn.service.SkinTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/skinTypes")
public class SkinTypeController {
    private final SkinTypeService skinTypeService;
    @GetMapping
    public ResponseEntity<List<SkinTypeDTO>> showAll(){
        return ResponseEntity.ok().body(skinTypeService.showAll());
    }
}
