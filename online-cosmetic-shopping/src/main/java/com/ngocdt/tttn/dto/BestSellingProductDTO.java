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
    private SkinTypeDTO skinType;
    private ToneDTO tone;
    private SizeDTO size;
    private CharacteristicDTO characteristic;
    private IngredientDTO ingredient;
    private BrandDTO brand;
    private OriginDTO origin;
    private CategoryDTO category;

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
        dto.setSkinType(SkinTypeDTO.toDTO(product.getSkinType()));
        dto.setTone(ToneDTO.toDTO(product.getTone()));
        dto.setSize(SizeDTO.toDTO(product.getSize()));
        dto.setBrand(BrandDTO.toDTO(product.getBrand()));
        dto.setOrigin(OriginDTO.toDTO(product.getOrigin()));
        dto.setIngredient(IngredientDTO.toDTO(product.getIngredient()));
        dto.setCharacteristic(CharacteristicDTO.toDTO(product.getCharacteristic()));
        dto.setCategory(CategoryDTO.toDTO(product.getCategory()));
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

    public SkinTypeDTO getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinTypeDTO skinType) {
        this.skinType = skinType;
    }

    public ToneDTO getTone() {
        return tone;
    }

    public void setTone(ToneDTO tone) {
        this.tone = tone;
    }

    public SizeDTO getSize() {
        return size;
    }

    public void setSize(SizeDTO size) {
        this.size = size;
    }

    public CharacteristicDTO getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicDTO characteristic) {
        this.characteristic = characteristic;
    }

    public IngredientDTO getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDTO ingredient) {
        this.ingredient = ingredient;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public OriginDTO getOrigin() {
        return origin;
    }

    public void setOrigin(OriginDTO origin) {
        this.origin = origin;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
