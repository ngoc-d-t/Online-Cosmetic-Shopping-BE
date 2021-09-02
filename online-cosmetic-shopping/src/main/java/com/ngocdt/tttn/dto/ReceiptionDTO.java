package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.OrderForSupplier;
import com.ngocdt.tttn.entity.Receiption;
import com.ngocdt.tttn.entity.ReceiptionDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class ReceiptionDTO {
    private int receiptionID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;
    private long totalPayment;
    private int employeeID;
    private int orderForSupplierID;
    private List<ReceiptionDetailDTO> receiptionDetails;
    public static ReceiptionDTO toDTO(Receiption receiption) {
        if (receiption == null)
            return null;
        ReceiptionDTO dto = new ReceiptionDTO();
        dto.setReceiptionID(receiption.getReceiptionID());
        dto.setDate(receiption.getDate());
        dto.setTotalPayment(receiption.getTotalPrice());
        dto.setEmployeeID(receiption.getEmployee().getEmployeeID());
        dto.setOrderForSupplierID(receiption.getOrderForSupplier().getOrderForSupplierID());
        if(receiption.getReceiptionDetails() == null)
            return dto;
        List<ReceiptionDetailDTO> details = new ArrayList<>();
        for (ReceiptionDetail detail:
             receiption.getReceiptionDetails()) {
            details.add(ReceiptionDetailDTO.toDTO(detail));
        }
        dto.setReceiptionDetails(details);
        return dto;
    }

    public static Receiption toEntity(ReceiptionDTO dto) {
        if (dto == null)
            return null;
        Receiption receiption = new Receiption();
        receiption.setReceiptionID(dto.getReceiptionID());
        receiption.setDate(dto.getDate());
        receiption.setTotalPrice(dto.getTotalPayment());

        Employee employee = new Employee();
        employee.setEmployeeID(dto.getEmployeeID());
        receiption.setEmployee(employee);

        OrderForSupplier orderForSupplier = new OrderForSupplier();
        orderForSupplier.setOrderForSupplierID(dto.getOrderForSupplierID());
        receiption.setOrderForSupplier(orderForSupplier);

        if(dto.getReceiptionDetails() == null)
            return receiption;
        List<ReceiptionDetail> details = new ArrayList<>();
        for (ReceiptionDetailDTO detail:
                dto.getReceiptionDetails()) {
            details.add(ReceiptionDetailDTO.toEntity(detail));
        }
        receiption.setReceiptionDetails(details);
        return receiption;
    }

    public int getReceiptionID() {
        return receiptionID;
    }

    public void setReceiptionID(int receiptionID) {
        this.receiptionID = receiptionID;
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

    public int getOrderForSupplierID() {
        return orderForSupplierID;
    }

    public void setOrderForSupplierID(int orderForSupplierID) {
        this.orderForSupplierID = orderForSupplierID;
    }

    public List<ReceiptionDetailDTO> getReceiptionDetails() {
        return receiptionDetails;
    }

    public void setReceiptionDetails(List<ReceiptionDetailDTO> receiptionDetails) {
        this.receiptionDetails = receiptionDetails;
    }
}
