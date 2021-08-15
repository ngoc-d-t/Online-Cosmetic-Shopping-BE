package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/addresses")
public class AddressController {
    private final AddressService addressService;
    @GetMapping()
    public ResponseEntity<List<AddressDTO>> showAll(){
        return ResponseEntity.ok().body(addressService.showAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(addressService.showOne(id));
    }
    @PostMapping()
    public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO dto){
        return ResponseEntity.ok().body(addressService.create(dto));
    }
    @PutMapping()
    public ResponseEntity<AddressDTO> update(@Valid @RequestBody AddressDTO dto){
        return ResponseEntity.ok().body(addressService.update(dto));
    }
    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id){
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
