package com.ngocdt.tttn.entity;

import javax.persistence.*;

@Entity
public class BestSellingProduct {
    @Id
    @Column
    private int productID;

    @Column(nullable = false)
    private String name;

    @Column
    private String branchOrigin;

    @Column
    private String whereProduction;

    @Column
    private int quantity;

    @Column
    private String volumn;

    @Column
    private String image;

    @Column
    private int sale_per_month;

    @Column(nullable=false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierID", nullable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;


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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
