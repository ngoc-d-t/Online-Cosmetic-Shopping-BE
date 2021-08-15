package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.OrderDetailForSupplier;
import com.ngocdt.tttn.entity.OrderForSupplier;
import com.ngocdt.tttn.entity.Supplier;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderForSupplierDTO {
    private int orderForSupplierID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private long totalPayment;
    private int employeeID;
    private int supplierID;
    private List<OrderDetailForSupplierDTO> orderDetailForSuppliers;

    public static OrderForSupplierDTO toDTO(OrderForSupplier od) {
        if (od == null)
            return null;
        OrderForSupplierDTO dto = new OrderForSupplierDTO();
        dto.setOrderForSupplierID(od.getOrderForSupplierID());
        dto.setDate(od.getDate());
        dto.setTotalPayment(od.getTotalPayment());

        dto.setEmployeeID(od.getEmployee().getEmployeeID());
        dto.setSupplierID(od.getSupplier().getSupplierID());
        List<OrderDetailForSupplierDTO> orderDetailsDTO = new ArrayList<>();
        if (od.getOrderDetailForSuppliers() == null)
            return dto;
        for (OrderDetailForSupplier orderDetail :
                od.getOrderDetailForSuppliers()) {
            orderDetailsDTO.add(OrderDetailForSupplierDTO.toDTO(orderDetail));
        }
        dto.setOrderDetailForSuppliers(orderDetailsDTO);

        return dto;
    }

    public static OrderForSupplier toEntity(OrderForSupplierDTO dto) {
        if (dto == null)
            return null;
        OrderForSupplier od = new OrderForSupplier();
        od.setOrderForSupplierID(dto.getOrderForSupplierID());
        od.setDate(dto.getDate());
        od.setTotalPayment(dto.getTotalPayment());

        Employee employee = new Employee();
        employee.setEmployeeID(dto.getEmployeeID());
        od.setEmployee(employee);

        Supplier supplier = new Supplier();
        supplier.setSupplierID(dto.getSupplierID());
        od.setSupplier(supplier);

        if (dto.getOrderDetailForSuppliers() == null)
            return od;
        List<OrderDetailForSupplier> orderDetails = new ArrayList<>();
        for (OrderDetailForSupplierDTO orderDetailDTO :
                dto.getOrderDetailForSuppliers()) {
            orderDetails.add(OrderDetailForSupplierDTO.toEntity(orderDetailDTO));
        }
        od.setOrderDetailForSuppliers(orderDetails);

        return od;
    }

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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public List<OrderDetailForSupplierDTO> getOrderDetailForSuppliers() {
        return orderDetailForSuppliers;
    }

    public void setOrderDetailForSuppliers(List<OrderDetailForSupplierDTO> orderDetailForSuppliers) {
        this.orderDetailForSuppliers = orderDetailForSuppliers;
    }
}
