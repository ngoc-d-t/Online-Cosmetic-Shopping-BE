package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.Invoice;
import com.ngocdt.tttn.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InvoiceDTO {
    private int invoiceID;
    private String taxCode;
    private Date date;
    private int orderID;
    private int employeeID;

    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null)
            return null;
        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceID(invoice.getInvoiceID());
        dto.setTaxCode(invoice.getTaxCode());
        dto.setDate(invoice.getDate());
        dto.setOrderID(invoice.getOrder().getOrderID());
        dto.setEmployeeID(invoice.getEmployee().getEmployeeID());
        return dto;
    }

    public static Invoice toEntity(InvoiceDTO dto) {
        if (dto == null)
            return null;
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(dto.getInvoiceID());
        invoice.setTaxCode(dto.getTaxCode());
        invoice.setDate(dto.getDate());

        Order order = new Order();
        order.setOrderID(dto.getOrderID());
        invoice.setOrder(order);

        if (dto.getEmployeeID() == 0)
            return invoice;
        Employee employee = new Employee();
        employee.setEmployeeID(dto.getEmployeeID());
        invoice.setEmployee(employee);

        return invoice;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
