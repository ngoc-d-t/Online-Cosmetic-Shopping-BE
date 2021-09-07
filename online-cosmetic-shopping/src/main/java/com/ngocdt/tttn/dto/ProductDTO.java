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
    private float discountPercent;
    private String volumn;
    @NotBlank(message = "Can not be empty.")
    private String image;
    private int supplierID;
    private SkinTypeDTO skinType;
    private ToneDTO tone;
    private SizeDTO size;
    private CharacteristicDTO characteristic;
    private IngredientDTO ingredient;
    private BrandDTO brand;
    private OriginDTO origin;
    private CategoryDTO category;

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
        dto.setDescription(pro.getDescription());
        dto.setImage(pro.getImage());
        dto.setCategoryID(pro.getCategory().getCategoryID());
        dto.setVolumn(pro.getVolumn());
        if(pro.getSupplier() == null)
            return dto;
        dto.setSupplierID(pro.getSupplier().getSupplierID());
        dto.setSkinType(SkinTypeDTO.toDTO(pro.getSkinType()));
        dto.setTone(ToneDTO.toDTO(pro.getTone()));
        dto.setSize(SizeDTO.toDTO(pro.getSize()));
        dto.setBrand(BrandDTO.toDTO(pro.getBrand()));
        dto.setOrigin(OriginDTO.toDTO(pro.getOrigin()));
        dto.setIngredient(IngredientDTO.toDTO(pro.getIngredient()));
        dto.setCharacteristic(CharacteristicDTO.toDTO(pro.getCharacteristic()));
        dto.setCategory(CategoryDTO.toDTO(pro.getCategory()));
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
        pro.setDescription(dto.getDescription());
        pro.setVolumn(dto.getVolumn());
        Category category = new Category();
        category.setCategoryID(dto.getCategoryID());
        pro.setCategory(category);
        return pro;
    }

    public SkinTypeDTO getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinTypeDTO skinType) {
        this.skinType = skinType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
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

    public ToneDTO getTone() {
        return tone;
    }

    public void setTone(ToneDTO tone) {
        this.tone = tone;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
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
}
