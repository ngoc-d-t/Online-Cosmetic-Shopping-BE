package com.ngocdt.tttn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    private String token;
    private AccountDTO account;
}
