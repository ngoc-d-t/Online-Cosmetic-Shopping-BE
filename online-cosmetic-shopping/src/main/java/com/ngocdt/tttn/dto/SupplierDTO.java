package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class SupplierDTO {
    private int supplierID;
    @NotBlank(message = "can not be empty.")
    private String companyName;
    @NotBlank(message = "can not be empty.")
    private String contactName;
    @NotBlank(message = "can not be empty.")
    private String address;
    @NotBlank(message = "can not be empty.")
    private String phoneNumber;

    public static SupplierDTO toDTO(Supplier sup) {
        if (sup == null) {
            return null;
        }
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierID(sup.getSupplierID());
        dto.setCompanyName(sup.getCompanyName());
        dto.setContactName(sup.getContactName());
        dto.setAddress(sup.getAddress());
        dto.setPhoneNumber(sup.getPhoneNumber());
        return dto;
    }

    public static Supplier toEntity(SupplierDTO dto) {
        if (dto == null) {
            return null;
        }
        Supplier sup = new Supplier();
        sup.setSupplierID(dto.getSupplierID());
        sup.setCompanyName(dto.getCompanyName());
        sup.setContactName(dto.getContactName());
        sup.setAddress(dto.getAddress());
        sup.setPhoneNumber(dto.getPhoneNumber());
        return sup;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
