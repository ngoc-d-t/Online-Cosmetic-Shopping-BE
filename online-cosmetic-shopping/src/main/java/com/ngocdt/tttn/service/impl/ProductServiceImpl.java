package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.dto.ProductPriceDTO;
import com.ngocdt.tttn.entity.*;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.exception.NotFoundException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.ProductService;
import com.ngocdt.tttn.utils.DriveAPIUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final AccountRepository accountRepo;
    private final ProductPriceRepository productPriceRepo;
    private final DiscountDetailRepository discountDetailRepo;
    private final SupplierRepository supplierRepo;

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public List<ProductDTO> showAll() {
        List<ProductDTO> productDTOSs = productRepo.findAll().stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            DiscountDetail discountDetail = discountDetailRepo
                    .findByProduct(dto.getProductID()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            ProductPrice productPrice = productPriceRepo
                    .findByProduct(e.getProductID());
            dto.setPrice(productPrice.getPrice());
            return dto;
        }).collect(Collectors.toList());
        //Collections.reverse(productDTOSs);
        return productDTOSs;
    }

    @Override
    public ProductDTO showOne(Integer id) {
        ProductDTO dto = ProductDTO.toDTO(productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found.")));
        DiscountDetail discountDetail = discountDetailRepo
                .findByProduct(
                        dto.getProductID()).orElse(null);
        if (discountDetail != null) {
            dto.setDiscountPercent(discountDetail.getDiscountPercent());
        }
        ProductPrice productPrice = productPriceRepo
                .findByProduct(id);
        dto.setPrice(productPrice.getPrice());

        return dto;
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO dto, HttpServletRequest request) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Bad request.");
        Product pro = ProductDTO.toEntity(dto);

        Product p = productRepo.findById(dto.getProductID()).get();
        if (!dto.getImage().equals(p.getImage())) {
            pro.setImage(uploadImage(dto.getImage()));
        } else pro.setImage(dto.getImage());
        Supplier supplier = supplierRepo.findById(dto.getSupplierID()).orElse(null);
        if(supplier == null)
            throw new BadRequestException("Not found supplier.");
        pro.setSupplier(supplier);
        pro = productRepo.save(pro);

        ProductPrice price = productPriceRepo.findByProduct(dto.getProductID());
        if (price == null) {
            price = new ProductPrice();
            price.setDate(new Date());
            price.setPrice(dto.getPrice());
            price.setProductID(dto.getProductID());
            price = productPriceRepo.save(price);
        } else {
            price.setPrice(dto.getPrice());
            price = productPriceRepo.save(price);
        }

        dto = ProductDTO.toDTO(pro);
        dto.setPrice(price.getPrice());
        return dto;
    }

    @Override
    @Transactional
    public ProductDTO create(ProductDTO dto, HttpServletRequest request) {
        Product pro = ProductDTO.toEntity(dto);
        pro.setProductID(0);

        pro.setImage(uploadImage(dto.getImage()));

        Supplier supplier = supplierRepo.findById(dto.getSupplierID()).orElse(null);
        if(supplier == null)
            throw new BadRequestException("Not found supplier.");
        pro.setSupplier(supplier);
        pro = productRepo.save(pro);

        ProductPriceDTO price = new ProductPriceDTO();
        price.setDate(new Date());
        price.setPrice(dto.getPrice());
        price.setProductID(pro.getProductID());
        price = createProductPrice(price);

        dto = ProductDTO.toDTO(pro);
        dto.setPrice(price.getPrice());
        return dto;
    }

    @Override
    public void delete(Integer id) {
        if (!productRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        List<ProductPrice> productPrices = productPriceRepo.findByProduct_ProductID(id);
        for (ProductPrice pp : productPrices
        ) {
            productPriceRepo.delete(pp);
        }
        productRepo.deleteById(id);
    }

    @Override
    public ProductPriceDTO createProductPrice(ProductPriceDTO dto) {
        ProductPrice productPrice = ProductPriceDTO.toEntity(dto);
        return ProductPriceDTO.toDTO(productPriceRepo.save(productPrice));
    }

    @Override
    public List<ProductDTO> showByCategory(Integer categoryID) {
        return productRepo.findByCategory_CategoryID(categoryID).stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            DiscountDetail discountDetail = discountDetailRepo
                    .findByProduct(
                            dto.getProductID()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            ProductPrice productPrice = productPriceRepo
                    .findByProduct(e.getProductID());
            dto.setPrice(productPrice.getPrice());
            return dto;
        })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> showByCategoryAndName(Integer categoryID, String value) {
        return productRepo.findByCategory_CategoryIDAndNameLike(categoryID, "%" + value + "%").stream()
                .map(e -> {
                    ProductDTO dto = ProductDTO.toDTO(e);
                    DiscountDetail discountDetail = discountDetailRepo
                            .findByProduct(
                                    dto.getProductID()).orElse(null);
                    if (discountDetail != null) {
                        dto.setDiscountPercent(discountDetail.getDiscountPercent());
                    }
                    ProductPrice productPrice = productPriceRepo
                            .findByProduct(e.getProductID());
                    dto.setPrice(productPrice.getPrice());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> showByName(String value) {
        return productRepo.findByNameLike("%" + value + "%").stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            DiscountDetail discountDetail = discountDetailRepo
                    .findByProduct(
                            dto.getProductID()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            ProductPrice productPrice = productPriceRepo
                    .findByProduct(e.getProductID());
            dto.setPrice(productPrice.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    private String uploadImage(String imagePath) {
        String fileName = UUID.randomUUID().toString();
        try {
            FileCopyUtils.copy(Base64Utils.decodeFromString(imagePath.split(",")[1]),
                    new File(this.fileUpload + fileName));
            return DriveAPIUtils.upload(new File(fileUpload + fileName));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadRequestException("image-wrong");
        }
    }
}
