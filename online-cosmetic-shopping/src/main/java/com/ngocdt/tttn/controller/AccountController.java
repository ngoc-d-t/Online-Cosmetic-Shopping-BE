package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/auth")
public class AccountController {
    private final AccountService accountService;
    @PostMapping("create")
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO dto){
        return ResponseEntity.ok().body(accountService.create(dto));
    }
    @PutMapping()
    public ResponseEntity<AccountDTO> update(@Valid @RequestBody AccountDTO dto, HttpServletRequest request){
        return ResponseEntity.ok().body(accountService.update(dto, request));
    }
}
