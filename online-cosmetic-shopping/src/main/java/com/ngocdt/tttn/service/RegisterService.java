package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.dto.RegisterDTO;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {
    RegisterDTO update(RegisterDTO dto, HttpServletRequest request);
    RegisterDTO create(RegisterDTO dto);
    void delete(HttpServletRequest request);
}
