package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.EmployeeDTO;
import com.ngocdt.tttn.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> showAll( Integer id) {
            return ResponseEntity.ok().body(employeeService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(employeeService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
