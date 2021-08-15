package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.ReceiptionDTO;
import com.ngocdt.tttn.service.ReceiptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/receiptions")
public class ReceiptionController {
    private final ReceiptionService receiptionService;

    @GetMapping()
    public ResponseEntity<List<ReceiptionDTO>> showAll(){
        return ResponseEntity.ok().body(receiptionService.showAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReceiptionDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(receiptionService.showOne(id));
    }
    @PostMapping()
    public ResponseEntity<ReceiptionDTO> create(@Valid @RequestBody ReceiptionDTO dto){
        return ResponseEntity.ok().body(receiptionService.create(dto));
    }
    @PutMapping()
    public ResponseEntity<ReceiptionDTO> update(@Valid @RequestBody ReceiptionDTO dto){
        return ResponseEntity.ok().body(receiptionService.update(dto));
    }
    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        receiptionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
