package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.BestSellingProduct;

public class BestSellingProductDTO {
    private int productID;
    private String name;
    private String branchOrigin;
    private String whereProduction;
    private int quantity;
    private String volumn;
    private String image;
    private int sale_per_month;
    private String description;
    private int supplierID;
    private int categoryID;
    private float price;
    private float discountPercent;

    public static BestSellingProductDTO toDTO(BestSellingProduct product) {
        if (product == null)
            return null;
        BestSellingProductDTO dto = new BestSellingProductDTO();
        dto.setProductID(product.getProductID());
        dto.setBranchOrigin(product.getBranchOrigin());
        dto.setCategoryID(product.getCategory().getCategoryID());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setSale_per_month(product.getSale_per_month());
        dto.setVolumn(product.getVolumn());
        dto.setWhereProduction(product.getWhereProduction());
        dto.setSupplierID(product.getSupplier().getSupplierID());
        return dto;
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

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSale_per_month() {
        return sale_per_month;
    }

    public void setSale_per_month(int sale_per_month) {
        this.sale_per_month = sale_per_month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
