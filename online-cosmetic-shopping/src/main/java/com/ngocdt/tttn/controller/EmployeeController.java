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
@RequestMapping("api/admin")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> showAllEmployees() {
            return ResponseEntity.ok().body(employeeService.showAllRoleAdminEmployees());
    }
    @GetMapping("/shippers")
    public ResponseEntity<List<EmployeeDTO>> showAllShippers() {
        return ResponseEntity.ok().body(employeeService.showAllShippers());
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(employeeService.showOne(id));
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.create(dto));
    }

    @PutMapping("/employees")
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.update(dto));
    }

    @DeleteMapping("/employees")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
