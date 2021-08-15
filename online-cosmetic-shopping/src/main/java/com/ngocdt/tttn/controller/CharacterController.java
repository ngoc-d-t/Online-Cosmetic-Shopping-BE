package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.CharacterDTO;
import com.ngocdt.tttn.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/admin/characters")
@Controller
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> showAll(){
        return ResponseEntity.ok().body(characterService.showAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> showOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(characterService.showOne(id));
    }
    @PostMapping()
    public ResponseEntity<CharacterDTO> create(@Valid @RequestBody CharacterDTO dto){
        return ResponseEntity.ok().body(characterService.create(dto));
    }
    @PutMapping()
    public ResponseEntity<CharacterDTO> update(@Valid @RequestBody CharacterDTO dto){
        return ResponseEntity.ok().body(characterService.update(dto));
    }
    @DeleteMapping()
    public ResponseEntity<CharacterDTO> delete(@RequestParam("id") Integer id){
        characterService.delete(id);
        return ResponseEntity.ok().build();
    }

}
