package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.enums.ROLE;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AccountDTO {
    private int accountID;
    @Email(message = "Not a email.")
    private String email;
    @Length(min = 8, max = 36, message = "Password is invalid.")
    private String password;
    private int employeeID;

    public static Account toEntity(AccountDTO dto) {
        if (dto == null)
            return null;
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public static AccountDTO toDTO(Account account) {
        if (account == null)
            return null;
        AccountDTO dto = new AccountDTO();
        dto.setEmail(account.getEmail());
        dto.setPassword(account.getPassword());

        if (account.getEmployee() != null)
            dto.setEmployeeID(account.getEmployee().getEmployeeID());
        return dto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

}
