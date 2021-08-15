package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.CustomerDTO;
import com.ngocdt.tttn.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> showAll(){
        return ResponseEntity.ok().body(customerService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(customerService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO dto){
        return ResponseEntity.ok().body(customerService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO dto){
        return ResponseEntity.ok().body(customerService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
