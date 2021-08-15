package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Category;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.entity.ProductPrice;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private int productID;
    @NotBlank(message = "can not be empty.")
    private String name;
    private String branchOrigin;
    private String whereProduction;
    private int quantity;
    private String direction;
    private String description;
    private Integer categoryID;
    private List<ProductPriceDTO> productPrices;
    private int employeeID;

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

        dto.setCategoryID(pro.getCategory().getCategoryID());

        dto.setEmployeeID(pro.getEmployee().getEmployeeID());

        if (pro.getProductPrices().size() == 0)
            return dto;
        List<ProductPriceDTO> pps = new ArrayList<>();
        for (ProductPrice pp : pro.getProductPrices()
        ) {
            ProductPriceDTO priceDTO = ProductPriceDTO.toDTO(pp);
            pps.add(priceDTO);
        }
        dto.setProductPrices(pps);
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

        Category category = new Category();
        category.setCategoryID(dto.getCategoryID());
        pro.setCategory(category);

        Employee employee = new Employee();
        employee.setEmployeeID(dto.getEmployeeID());
        pro.setEmployee(employee);

        if (dto.getProductPrices().size() == 0)
            return pro;
        List<ProductPrice> pps = new ArrayList<>();
        for (ProductPriceDTO pp : dto.getProductPrices()
        ) {
            ProductPrice price = ProductPriceDTO.toEntity(pp);
            pps.add(price);
        }
        pro.setProductPrices(pps);
        return pro;
    }

    public List<ProductPriceDTO> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPriceDTO> productPrices) {
        this.productPrices = productPrices;
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
}
