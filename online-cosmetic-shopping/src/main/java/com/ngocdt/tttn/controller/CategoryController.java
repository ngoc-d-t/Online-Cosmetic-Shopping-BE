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
@RequestMapping("/api/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> showAll(){
        return ResponseEntity.ok().body(categoryService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoryService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok().body(categoryService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok().body(categoryService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
