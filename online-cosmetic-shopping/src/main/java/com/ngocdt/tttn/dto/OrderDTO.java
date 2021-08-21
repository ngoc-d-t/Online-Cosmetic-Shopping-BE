package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Order;
import com.ngocdt.tttn.entity.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int orderID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private float totalPrice;
    private float totalDiscount;
    private int customerID;
    private int employeeID;
    private AddressDTO address;
    private float paid;
    private List<OrderDetailDTO> orderDetails;

    public static OrderDTO toDTO(Order order) {
        if (order == null)
            return null;
        OrderDTO dto = new OrderDTO();
        dto.setOrderID(order.getOrderID());
        dto.setDate(order.getDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setTotalDiscount(order.getTotalDiscount());
        AddressDTO add = new AddressDTO();
        add.setReceiverAddress(order.getReceiverAddress());
        add.setReceiverName(order.getReceiverName());
        add.setPhoneNumber(order.getPhoneNumber());
        dto.setAddress(add);
        dto.setPaid(order.getPaid());
        List<OrderDetailDTO> detailDTOS = new ArrayList<>();
        for (OrderDetail detail : order.getOrderDetails()
        ) {
            detailDTOS.add(OrderDetailDTO.toDTO(detail));
        }
        dto.setCustomerID(order.getCustomer().getCustomerID());
        dto.setOrderDetails(detailDTOS);
        return dto;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }
}
