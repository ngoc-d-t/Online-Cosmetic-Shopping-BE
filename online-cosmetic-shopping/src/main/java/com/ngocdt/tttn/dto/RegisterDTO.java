package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Address;
import com.ngocdt.tttn.entity.Customer;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterDTO {
    @NotBlank(message = "can not be empty.")
    private String fullname;
    private Date birthday;
    private int sex;
    @Email(message = "Not a email.")
    private String email;
    @Length(min = 8, max = 36, message = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    private String address;

    public static Account toAccountEntity(RegisterDTO dto){
        if(dto == null)
            return null;
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }
    public static Customer toCustomerEntity(RegisterDTO dto){
        if(dto == null)
            return null;
        Customer customer = new Customer();
        customer.setFullname(dto.getFullname());
        customer.setBirthday(dto.getBirthday());
        customer.setSex(dto.getSex());

        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setName(dto.getFullname());
        address.setAddress(dto.getAddress());

        addresses.add(address);
        customer.setAddresses(addresses);
        return customer;
    }

    public  static RegisterDTO toDTO(Customer customer, Account account){
        if(customer == null || account == null)
            return null;
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(account.getEmail());
        registerDTO.setPassword(account.getPassword());
        registerDTO.setBirthday(customer.getBirthday());
        registerDTO.setSex(customer.getSex());
        registerDTO.setFullname(customer.getFullname());

        registerDTO.setPhoneNumber(customer.getAddresses().stream().findFirst().orElse(null).getPhoneNumber());
        registerDTO.setAddress(customer.getAddresses().stream().findFirst().orElse(null).getAddress());
        return registerDTO;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
