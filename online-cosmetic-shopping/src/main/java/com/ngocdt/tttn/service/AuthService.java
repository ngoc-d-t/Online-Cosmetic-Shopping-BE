package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.dto.RegisterDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService{
    String signIn(AccountDTO dto);
    RegisterDTO register(RegisterDTO dto);
    RegisterDTO update(RegisterDTO dto, HttpServletRequest request);
}
