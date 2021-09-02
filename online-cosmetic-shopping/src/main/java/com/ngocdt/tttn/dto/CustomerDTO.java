package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Address;
import com.ngocdt.tttn.entity.Category;
import com.ngocdt.tttn.entity.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private int customerID;
    @NotBlank(message = "can not be empty.")
    private String fullname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date birthday;
    private int sex;
    private List<AddressDTO> addresses;

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerID(customer.getCustomerID());
        dto.setFullname(customer.getFullname());
        dto.setBirthday(customer.getBirthday());
        dto.setSex(customer.getSex());
        if (customer.getAddresses().size() == 0)
            return dto;
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (Address add : customer.getAddresses()
        ) {
            addressDTOS.add(AddressDTO.toDTO(add));
        }
        dto.setAddresses(addressDTOS);
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerID(dto.getCustomerID());
        customer.setFullname(dto.getFullname());
        customer.setBirthday(dto.getBirthday());
        customer.setSex(dto.getSex());
        if (dto.getAddresses().size() == 0)
            return customer;
        List<Address> addr = new ArrayList<>();
        for (AddressDTO add : dto.getAddresses()
        ) {
            addr.add(AddressDTO.toEntity(add));
        }
        customer.setAddresses(addr);
        return customer;
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

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
