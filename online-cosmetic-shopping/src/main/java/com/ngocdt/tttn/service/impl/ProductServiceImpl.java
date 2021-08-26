package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.dto.ProductPriceDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.DiscountDetailRepository;
import com.ngocdt.tttn.repository.ProductPriceRepository;
import com.ngocdt.tttn.repository.ProductRepository;
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

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public List<ProductDTO> showAll() {
        List<ProductDTO> productDTOSs = productRepo.findAll().stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            DiscountDetail discountDetail = discountDetailRepo
                    .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                            dto.getProductID(), new Date(), new Date()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            List<ProductPrice> productPrice = productPriceRepo
                    .findByProduct_ProductIDAndAndDateIsLessThanEqual(e.getProductID(), new Date());
            dto.setPrice(productPrice.get(productPrice.size() - 1).getPrice());
            return dto;
        }).collect(Collectors.toList());
        return productDTOSs;
    }

    @Override
    public ProductDTO showOne(Integer id) {
        ProductDTO dto = ProductDTO.toDTO(productRepo.findById(id).orElse(null));
        DiscountDetail discountDetail = discountDetailRepo
                .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                        dto.getProductID(), new Date(), new Date()).orElse(null);
        if (discountDetail != null) {
            dto.setDiscountPercent(discountDetail.getDiscountPercent());
        }
        List<ProductPrice> productPrice = productPriceRepo
                .findByProduct_ProductIDAndAndDateIsLessThanEqual(id, new Date());
        dto.setPrice(productPrice.get(productPrice.size() - 1).getPrice());

        return dto;
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO dto, HttpServletRequest request) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Bad request.");
        Account account = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Product pro = ProductDTO.toEntity(dto);
        pro.setEmployee(account.getEmployee());
        pro.setImage(uploadImage(dto.getImage()));
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
    @Transactional
    public ProductDTO create(ProductDTO dto, HttpServletRequest request) {
        Account account = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Product pro = ProductDTO.toEntity(dto);
        pro.setEmployee(account.getEmployee());
        pro.setProductID(0);
        pro.setImage(uploadImage(dto.getImage()));
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
                    .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                            dto.getProductID(), new Date(), new Date()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            List<ProductPrice> productPrice = productPriceRepo
                    .findByProduct_ProductIDAndAndDateIsLessThanEqual(e.getProductID(), new Date());
            dto.setPrice(productPrice.get(productPrice.size() - 1).getPrice());
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
                            .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                                    dto.getProductID(), new Date(), new Date()).orElse(null);
                    if (discountDetail != null) {
                        dto.setDiscountPercent(discountDetail.getDiscountPercent());
                    }
                    List<ProductPrice> productPrice = productPriceRepo
                            .findByProduct_ProductIDAndAndDateIsLessThanEqual(e.getProductID(), new Date());
                    dto.setPrice(productPrice.get(productPrice.size() - 1).getPrice());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> showByName(String value) {
        return productRepo.findByNameLike("%" + value + "%").stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            DiscountDetail discountDetail = discountDetailRepo
                    .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                            dto.getProductID(), new Date(), new Date()).orElse(null);
            if (discountDetail != null) {
                dto.setDiscountPercent(discountDetail.getDiscountPercent());
            }
            List<ProductPrice> productPrice = productPriceRepo
                    .findByProduct_ProductIDAndAndDateIsLessThanEqual(e.getProductID(), new Date());
            dto.setPrice(productPrice.get(productPrice.size() - 1).getPrice());
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
