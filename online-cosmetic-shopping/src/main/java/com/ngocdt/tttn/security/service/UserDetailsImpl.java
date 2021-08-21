package com.ngocdt.tttn.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Customer;
import com.ngocdt.tttn.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int accountID;
    private String email;
    @JsonIgnore
    private String password;
    private Employee employee;
    private Customer customer;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int accountID, String email, String password, Employee employee, Customer customer,
                           Collection<? extends GrantedAuthority> authorities) {
        super();
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.employee = employee;
        this.customer = customer;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(account.getRole().getRoleName().name()));
        return new UserDetailsImpl(account.getAccountID(), account.getEmail(), account.getPassword(),
                account.getEmployee(), account.getCustomer(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(accountID, user.getAccountID());
    }
}