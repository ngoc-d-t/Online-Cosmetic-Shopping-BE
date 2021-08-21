package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.entity.Account;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService{
    AccountDTO update(AccountDTO dto,HttpServletRequest request);
    AccountDTO create(AccountDTO dto);
    void delete(HttpServletRequest request);
    String showUserName(Account account);
}
