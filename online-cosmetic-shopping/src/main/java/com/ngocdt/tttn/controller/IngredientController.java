package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.IngredientDTO;
import com.ngocdt.tttn.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    @GetMapping()
    public ResponseEntity<List<IngredientDTO>> showAll(){
        return ResponseEntity.ok().body(ingredientService.showAll());
    }
}
