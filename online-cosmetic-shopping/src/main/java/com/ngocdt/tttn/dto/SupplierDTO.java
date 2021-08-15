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
    private String name;
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
        dto.setName(sup.getName());
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
        sup.setName(dto.getName());
        sup.setAddress(dto.getAddress());
        sup.setPhoneNumber(dto.getPhoneNumber());
        return sup;
    }
}
