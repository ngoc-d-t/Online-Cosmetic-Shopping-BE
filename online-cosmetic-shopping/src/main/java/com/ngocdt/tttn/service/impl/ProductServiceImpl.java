package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.dto.ProductPriceDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.ProductPriceRepository;
import com.ngocdt.tttn.repository.ProductRepository;
import com.ngocdt.tttn.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Override
    public List<ProductDTO> showAll() {
        return productRepo.findAll().stream().map(ProductDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO showOne(Integer id) {
        return ProductDTO.toDTO(productRepo.findById(id).orElse(null));
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Bad request.");
        Product pro = ProductDTO.toEntity(dto);
        return ProductDTO.toDTO(productRepo.save(pro));
    }

    @Override
    public ProductDTO create(ProductDTO dto, HttpServletRequest request) {
        Account account = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Product pro = ProductDTO.toEntity(dto);
        pro.setEmployee(account.getEmployee());
        pro.setProductID(0);
        Product resProduct = productRepo.save(pro);
        List<ProductPriceDTO> priceDTOS = new ArrayList<>();
        for (ProductPriceDTO ppDTO : dto.getProductPrices()
        ) {
            ppDTO.setProductID(resProduct.getProductID());
            ppDTO.setDate(new Date());
            priceDTOS.add(createProductPrice(ppDTO));
        }
        ProductDTO productDTO = ProductDTO.toDTO(resProduct);
        productDTO.setProductPrices(priceDTOS);
        return productDTO;
    }

    @Override
    public void delete(Integer id) {
        if (!productRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        productRepo.deleteById(id);
    }

    @Override
    public ProductPriceDTO createProductPrice(ProductPriceDTO dto) {
        System.out.println(dto.getDate());
        ProductPrice productPrice = ProductPriceDTO.toEntity(dto);
        return ProductPriceDTO.toDTO(productPriceRepo.save(productPrice));
    }

    @Override
    public List<ProductDTO> showByCategory(Integer categoryID) {
        return productRepo.findByCategory_CategoryID(categoryID).stream().map(ProductDTO::toDTO).collect(Collectors.toList());
    }
}
