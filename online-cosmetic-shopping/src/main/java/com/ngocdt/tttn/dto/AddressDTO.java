package com.ngocdt.tttn.dto;


import com.ngocdt.tttn.entity.Address;
import com.ngocdt.tttn.entity.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class AddressDTO {
    private int addressID;
    @NotBlank(message = "Can be not empty.")
    private String phoneNumber;
    @NotBlank(message = "Can be not empty.")
    private String receiverName;
    @NotBlank(message = "Can be not empty.")
    private String receiverAddress;
    private int customerID;

    public static AddressDTO toDTO(Address address){
        if(address == null)
            return null;
        AddressDTO dto = new AddressDTO();
        dto.setAddressID(address.getAddressID());
        dto.setPhoneNumber(address.getPhoneNumber());
        dto.setReceiverName(address.getReceiverName());
        dto.setReceiverAddress(address.getAddress());
        if(address.getCustomer() == null)
            return dto;
        dto.setCustomerID(address.getCustomer().getCustomerID());
        return dto;
    }
    public static Address toEntity(AddressDTO dto){
        if(dto == null)
            return null;
        Address address = new Address();
        address.setAddressID(dto.getAddressID());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setReceiverName(dto.getReceiverName());
        address.setAddress(dto.getReceiverAddress());
        if(dto.getCustomerID() == 0)
            return address;
        Customer customer = new Customer();
        customer.setCustomerID(dto.getCustomerID());
        address.setCustomer(customer);

        return address;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
