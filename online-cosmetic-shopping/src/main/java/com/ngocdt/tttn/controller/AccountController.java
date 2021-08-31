package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.security.service.UserDetailsImpl;
import com.ngocdt.tttn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("api")
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/auth/create")
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO dto){
        return ResponseEntity.ok().body(accountService.create(dto));
    }
    @PutMapping("/auth")
    public ResponseEntity<AccountDTO> update(@Valid @RequestBody AccountDTO dto, HttpServletRequest request){
        return ResponseEntity.ok().body(accountService.update(dto, request));
    }
    @GetMapping("/accounts")
    public ResponseEntity<String> getUsername(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return ResponseEntity.ok().body(accountService.showUserName(userDetails.getAccountID()));
    }

}
