package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.CategoryDTO;
import com.ngocdt.tttn.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<CategoryDTO>> showAll(){
        return ResponseEntity.ok().body(categoryService.showAll());
    }

    @GetMapping("/public/categories/{id}")
    public ResponseEntity<CategoryDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoryService.showOne(id));
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok().body(categoryService.create(dto));
    }

    @PutMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok().body(categoryService.update(dto));
    }

    @DeleteMapping("/admin/categories")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
