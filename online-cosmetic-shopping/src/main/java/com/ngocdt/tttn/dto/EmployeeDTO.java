package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Category;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class EmployeeDTO {
    private int employeeID;
    @NotBlank(message="can not be empty.")
    private String name;
    private String address;
    private String phoneNumber;
    private int sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date birthday;
    public static EmployeeDTO toDTO(Employee empl){
        if(empl == null){
            return null;
        }
        EmployeeDTO dto=new EmployeeDTO();
        dto.setEmployeeID(empl.getEmployeeID());
        dto.setName(empl.getFullname());
        dto.setAddress(empl.getAddress());
        dto.setPhoneNumber(empl.getPhoneNumber());
        dto.setSex(empl.getSex());
        dto.setBirthday(empl.getBirthday());
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto){
        if(dto == null){
            return null;
        }
        Employee empl=new Employee();
        empl.setEmployeeID(dto.getEmployeeID());
        empl.setFullname(dto.getName());
        empl.setAddress(dto.getAddress());
        empl.setPhoneNumber(dto.getPhoneNumber());
        empl.setSex(dto.getSex());
        empl.setBirthday(dto.getBirthday());
        return empl;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
