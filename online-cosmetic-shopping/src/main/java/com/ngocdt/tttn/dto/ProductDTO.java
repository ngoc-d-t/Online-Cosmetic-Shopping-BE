package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private int productID;
    @NotBlank(message = "Can not be empty.")
    private String name;
    private String branchOrigin;
    private String whereProduction;
    private int quantity;
    private String direction;
    private String description;
    private Integer categoryID;
    private float price;
    private int employeeID;
    private float discountPercent;
    private String volumn;
    @NotBlank(message = "Can not be empty.")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static ProductDTO toDTO(Product pro) {
        if (pro == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductID(pro.getProductID());
        dto.setName(pro.getName());
        dto.setBranchOrigin(pro.getBranchOrigin());
        dto.setWhereProduction(pro.getWhereProduction());
        dto.setQuantity(pro.getQuantity());
        dto.setDirection(pro.getDirection());
        dto.setDescription(pro.getDescription());
        dto.setImage(pro.getImage());
        dto.setCategoryID(pro.getCategory().getCategoryID());
        dto.setVolumn(pro.getVolumn());
        dto.setEmployeeID(pro.getEmployee().getEmployeeID());

        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        Product pro = new Product();
        pro.setProductID(dto.getProductID());
        pro.setName(dto.getName());
        pro.setBranchOrigin(dto.getBranchOrigin());
        pro.setWhereProduction(dto.getWhereProduction());
        pro.setQuantity(dto.getQuantity());
        pro.setDirection(dto.getDirection());
        pro.setDescription(dto.getDescription());
        pro.setVolumn(dto.getVolumn());
        Category category = new Category();
        category.setCategoryID(dto.getCategoryID());
        pro.setCategory(category);

        Employee employee = new Employee();
        employee.setEmployeeID(dto.getEmployeeID());
        pro.setEmployee(employee);

        return pro;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchOrigin() {
        return branchOrigin;
    }

    public void setBranchOrigin(String branchOrigin) {
        this.branchOrigin = branchOrigin;
    }

    public String getWhereProduction() {
        return whereProduction;
    }

    public void setWhereProduction(String whereProduction) {
        this.whereProduction = whereProduction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }
}
