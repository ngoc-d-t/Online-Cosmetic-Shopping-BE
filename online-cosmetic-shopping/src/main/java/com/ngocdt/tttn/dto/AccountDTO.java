package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ngocdt.tttn.entity.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class AccountDTO {
    private int accountID;
    @Email(message = "Not a email.")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 8, max = 36, message = "Password is invalid.")
    private String password;
    private EmployeeDTO employee;
    private CustomerDTO customer;
    private String role;

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
        dto.setAccountID(account.getAccountID());
        dto.setEmail(account.getEmail());
        dto.setPassword(account.getPassword());
        dto.setEmployee(EmployeeDTO.toDTO(account.getEmployee()));
        dto.setCustomer(CustomerDTO.toDTO(account.getCustomer()));
        if (account.getRole() != null)
            dto.setRole(account.getRole().getRoleName().toString());
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

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
