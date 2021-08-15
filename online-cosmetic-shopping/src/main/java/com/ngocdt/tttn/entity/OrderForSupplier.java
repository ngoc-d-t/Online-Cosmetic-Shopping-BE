package com.ngocdt.tttn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderForSupplier")
@Getter
@Setter
public class OrderForSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int orderForSupplierID;

    @Column
    private Date date = new Date();

    @Column
    private long totalPayment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeID", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierID", nullable = false)
    private Supplier supplier;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderForSupplier")
    private List<OrderDetailForSupplier> orderDetailForSuppliers;

    public int getOrderForSupplierID() {
        return orderForSupplierID;
    }

    public void setOrderForSupplierID(int orderForSupplierID) {
        this.orderForSupplierID = orderForSupplierID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(long totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<OrderDetailForSupplier> getOrderDetailForSuppliers() {
        return orderDetailForSuppliers;
    }

    public void setOrderDetailForSuppliers(List<OrderDetailForSupplier> orderDetailForSuppliers) {
        this.orderDetailForSuppliers = orderDetailForSuppliers;
    }
}
