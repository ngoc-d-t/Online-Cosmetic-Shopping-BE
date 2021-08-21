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
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final AccountRepository accountRepo;
    private final ProductPriceRepository productPriceRepo;
    private final DiscountDetailRepository discountDetailRepo;

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
            ProductPrice productPrice = productPriceRepo
                    .findTop1ByProduct_ProductIDAndDateLessThanEqual(e.getProductID(), new Date());
            dto.setPrice(productPrice.getPrice());
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
        ProductPrice productPrice = productPriceRepo.findTop1ByProduct_ProductIDAndDateLessThanEqual(id, new Date());
        dto.setPrice(productPrice.getPrice());
        return dto;
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Bad request.");
        Product pro = ProductDTO.toEntity(dto);
        return ProductDTO.toDTO(productRepo.save(pro));
    }

    @Override
    @Transactional
    public ProductDTO create(ProductDTO dto, HttpServletRequest request) {
        Account account = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Product pro = ProductDTO.toEntity(dto);
        pro.setEmployee(account.getEmployee());
        pro.setProductID(0);
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
            ProductPrice productPrice = productPriceRepo.findTop1ByProduct_ProductIDAndDateLessThanEqual(e.getProductID()
                    , new Date());
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
                            .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                                    dto.getProductID(), new Date(), new Date()).orElse(null);
                    if (discountDetail != null) {
                        dto.setDiscountPercent(discountDetail.getDiscountPercent());
                    }
                    ProductPrice productPrice = productPriceRepo
                            .findTop1ByProduct_ProductIDAndDateLessThanEqual(e.getProductID(), new Date());
                    dto.setPrice(productPrice.getPrice());
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
            ProductPrice productPrice = productPriceRepo
                    .findTop1ByProduct_ProductIDAndDateLessThanEqual(e.getProductID(), new Date());
            dto.setPrice(productPrice.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }
}
