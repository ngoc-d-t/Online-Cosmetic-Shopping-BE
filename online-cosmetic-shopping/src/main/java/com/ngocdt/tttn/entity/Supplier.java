package com.ngocdt.tttn.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Supplier")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int supplierID;

    @Column
    private String companyName;

    @Column
    private String contactName;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private List<OrderForSupplier> orderForSuppliers;

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
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

    public List<OrderForSupplier> getOrderForSuppliers() {
        return orderForSuppliers;
    }

    public void setOrderForSuppliers(List<OrderForSupplier> orderForSuppliers) {
        this.orderForSuppliers = orderForSuppliers;
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
}
