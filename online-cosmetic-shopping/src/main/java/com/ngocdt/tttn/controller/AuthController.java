package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.dto.RegisterDTO;
import com.ngocdt.tttn.service.AuthService;
import com.ngocdt.tttn.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<String> signin(@Valid @RequestBody  AccountDTO dto){
        return ResponseEntity.ok().body(authService.signIn(dto));
    }

    @PostMapping("register")
    public ResponseEntity<RegisterDTO> register(@Valid @RequestBody RegisterDTO dto){
        return ResponseEntity.ok().body(authService.register(dto));
    }
}
