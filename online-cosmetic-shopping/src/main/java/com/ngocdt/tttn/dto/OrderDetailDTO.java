package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Order;
import com.ngocdt.tttn.entity.OrderDetail;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.repository.DiscountDetailRepository;

public class OrderDetailDTO {
    private int orderDetailID;
    private float price;
    private float discount;
    private int quantity;
    private int orderID;
    private ProductDTO product;
    public static OrderDetailDTO toDTO(OrderDetail orderDetail) {
        if (orderDetail == null)
            return null;
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderDetailID(orderDetail.getOrderDetailID());
        dto.setPrice(orderDetail.getPrice());
        dto.setDiscount(orderDetail.getDiscount());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setOrderID(orderDetail.getOrder().getOrderID());
        ProductDTO pro = ProductDTO.toDTO(orderDetail.getProduct());
        dto.setProduct(pro);
        return dto;
    }

    public static OrderDetail toEntity(OrderDetailDTO dto) {
        if (dto == null)
            return null;
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailID(dto.getOrderDetailID());
        orderDetail.setPrice(dto.getPrice());
        orderDetail.setDiscount(dto.getDiscount());
        orderDetail.setQuantity(dto.getQuantity());

        Order order = new Order();
        order.setOrderID(dto.getOrderID());
        orderDetail.setOrder(order);

        Product product = new Product();
        product.setProductID(dto.getProduct().getProductID());
        orderDetail.setProduct(product);
        return orderDetail;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
