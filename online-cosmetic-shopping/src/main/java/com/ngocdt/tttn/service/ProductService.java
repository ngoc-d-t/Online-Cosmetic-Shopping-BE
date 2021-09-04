package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.BestSellingProductDTO;
import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.dto.ProductPriceDTO;
import com.ngocdt.tttn.entity.Product;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {
    List<ProductDTO> showByCategory(Integer categoryID);
    List<ProductDTO> showByCategoryAndName(Integer categoryID, String value);
    List<ProductDTO> showByName(String value);
    List<ProductDTO> showAll();
    ProductDTO showOne(Integer id);
    ProductDTO update(ProductDTO dto, HttpServletRequest request);
    ProductDTO create(ProductDTO dto, HttpServletRequest request);
    void delete(Integer id);

    ProductPriceDTO createProductPrice(ProductPriceDTO dto);

    List<BestSellingProductDTO> showBestSellingProducts();

    List<ProductDTO> showAllAdmin();
}
