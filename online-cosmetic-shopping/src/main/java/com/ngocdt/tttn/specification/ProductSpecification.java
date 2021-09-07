package com.ngocdt.tttn.specification;

import com.ngocdt.tttn.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {
    public static Specification<Product> getFilter(String q, Integer originID, Integer categoryID, Integer brandID
            , Integer skinID, Integer toneID, Integer ingredientID, Integer characteristicID, Integer sizeID) {
        Specification<Product> specification = Specification.where(null);
        if (!q.isEmpty())
            specification = specification.and(nameLike(q));
        if (originID > 0)
            specification = specification.and(belongToOrigin(originID));
        if (categoryID > 0)
            specification = specification.and(belongToCategory(categoryID));
        if (brandID > 0)
            specification = specification.and(belongToBrand(brandID));
        if (skinID > 0)
            specification = specification.and(belongToSkin(skinID));
        if (toneID > 0)
            specification = specification.and(belongToTone(toneID));
        if (ingredientID > 0)
            specification = specification.and(belongToIngredient(ingredientID));
        if (characteristicID > 0)
            specification = specification.and(belongToCharacteristic(characteristicID));
        if (sizeID > 0)
            specification = specification.and(belongToSize(sizeID));
        return specification;
    }

    static Specification<Product> nameLike(String q) {
        return (product, cq, cb) -> cb.like(product.get("otherName"), "%" + q + "%");
    }

    static Specification<Product> belongToOrigin(Integer originID) {
        return (product, cq, cb) -> cb.equal(product.get("origin").get("id"), originID);
    }

    static Specification<Product> belongToCategory(Integer categoryID) {
        return (product, cq, cb) -> cb.equal(product.get("category").get("categoryID"), categoryID);
    }

    static Specification<Product> belongToBrand(Integer brandID) {
        return (product, cq, cb) -> cb.equal(product.get("brand").get("id"), brandID);
    }

    static Specification<Product> belongToSkin(Integer skinID) {
        return (product, cq, cb) -> cb.equal(product.get("skinType").get("id"), skinID);
    }

    static Specification<Product> belongToTone(Integer toneID) {
        return (product, cq, cb) -> cb.equal(product.get("tone").get("id"), toneID);
    }

    static Specification<Product> belongToIngredient(Integer ingredientID) {
        return (product, cq, cb) -> cb.equal(product.get("ingredient").get("id"), ingredientID);
    }

    static Specification<Product> belongToCharacteristic(Integer characteristicID) {
        return (product, cq, cb) -> cb.equal(product.get("characteristic").get("id"), characteristicID);
    }

    static Specification<Product> belongToSize(Integer sizeID) {
        return (product, cq, cb) -> cb.equal(product.get("size").get("id"), sizeID);
    }
}
