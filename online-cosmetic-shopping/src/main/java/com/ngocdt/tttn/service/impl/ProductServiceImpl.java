package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.BestSellingProductDTO;
import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.dto.ProductPriceDTO;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.entity.Supplier;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.exception.NotFoundException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.ProductService;
import com.ngocdt.tttn.specification.ProductSpecification;
import com.ngocdt.tttn.utils.DriveAPIUtils;
import com.ngocdt.tttn.utils.VNCharacterUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
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
    private final BestSellingProductRepository bestSellingProductRepo;
    private final BrandRepository brandRepo;
    private final OriginRepository originRepo;
    private final CharacteristicRepository characteristicRepo;
    private final SkinTypeRepository skinTypeRepo;
    private final ToneRepository toneRepo;
    private final IngredientRepository ingredientRepo;
    private final SizeRepository sizeRepo;
    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public List<ProductDTO> showAll() {
        List<ProductDTO> productDTOSs = productRepo.findAllByQuantityIsGreaterThan(0).stream().map(e -> {
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
        Collections.reverse(productDTOSs);
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
        if (supplier == null)
            throw new BadRequestException("Not found supplier.");
        pro.setSupplier(supplier);
        String otherName = VNCharacterUtils.removeAccent(dto.getName().toLowerCase())
                .replace(" ", "");
        pro.setOtherName(otherName);
        pro.setBrand(brandRepo.findById(dto.getBrand().getId())
                .orElseThrow(()-> new NotFoundException("Brand not found.")));
        pro.setOrigin(originRepo.findById(dto.getOrigin().getId())
                .orElseThrow(()-> new NotFoundException("Origin not found.")));
        pro.setIngredient(ingredientRepo.findById(dto.getIngredient().getId())
                .orElseThrow(() -> new NotFoundException("Ingredient not found.")));
        pro.setTone(toneRepo.findById(dto.getTone().getId())
                .orElseThrow(()-> new NotFoundException("Tone not found.")));
        pro.setSize(sizeRepo.findById(dto.getSize().getId())
                .orElseThrow(()-> new NotFoundException("Size not found.")));
        pro.setSkinType(skinTypeRepo.findById(dto.getSkinType().getId())
                .orElseThrow(()-> new NotFoundException("Skin type not found.")));
        pro.setCharacteristic(characteristicRepo.findById(dto.getCharacteristic().getId())
                .orElseThrow(()-> new NotFoundException("Characteristic not found.")));
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
        if (supplier == null)
            throw new BadRequestException("Not found supplier.");
        pro.setSupplier(supplier);
        String otherName = VNCharacterUtils.removeAccent(dto.getName().toLowerCase())
                .replace(" ", "");
        pro.setOtherName(otherName);
        pro.setBrand(brandRepo.findById(dto.getBrand().getId())
                .orElseThrow(()-> new NotFoundException("Brand not found.")));
        pro.setOrigin(originRepo.findById(dto.getOrigin().getId())
                .orElseThrow(()-> new NotFoundException("Origin not found.")));
        pro.setIngredient(ingredientRepo.findById(dto.getIngredient().getId())
                .orElseThrow(() -> new NotFoundException("Ingredient not found.")));
        pro.setTone(toneRepo.findById(dto.getTone().getId())
                .orElseThrow(()-> new NotFoundException("Tone not found.")));
        pro.setSize(sizeRepo.findById(dto.getSize().getId())
                .orElseThrow(()-> new NotFoundException("Size not found.")));
        pro.setSkinType(skinTypeRepo.findById(dto.getSkinType().getId())
                .orElseThrow(()-> new NotFoundException("Skin type not found.")));
        pro.setCharacteristic(characteristicRepo.findById(dto.getCharacteristic().getId())
                .orElseThrow(()-> new NotFoundException("Characteristic not found.")));
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
    public List<BestSellingProductDTO> showBestSellingProducts() {
        List<BestSellingProductDTO> productDTOSs = bestSellingProductRepo.findBestSelling().stream().map(e -> {
            BestSellingProductDTO dto = BestSellingProductDTO.toDTO(e);
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
        return productDTOSs;
    }

    @Override
    public List<ProductDTO> showAllAdmin() {
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
        Collections.reverse(productDTOSs);
        return productDTOSs;
    }

    @Override
    public List<ProductDTO> showDiscountProduct() {
        List<ProductDTO> productDTOSs = productRepo.findAllByDiscount().stream().map(e -> {
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
        return productDTOSs;
    }

    @Override
    public List<ProductDTO> search(String q, Integer originID, Integer categoryID, Integer brandID, Integer skinID,
                                   Integer toneID, Integer ingredientID, Integer characteristicID, Integer sizeID,
                                   String sort) {
        q = VNCharacterUtils.removeAccent(q).replace(" ", "").toLowerCase();
        if (!sort.equalsIgnoreCase("DESC") && !sort.equalsIgnoreCase("ASC"))
            throw new BadRequestException("sort invalid");
        List<ProductDTO> results = productRepo.findAll(ProductSpecification.getFilter(q, originID, categoryID, brandID,
                skinID, toneID, ingredientID, characteristicID, sizeID)).stream().map(e -> {
            ProductDTO dto = ProductDTO.toDTO(e);
            discountDetailRepo
                    .findByProduct(dto.getProductID())
                    .ifPresent(discountDetail -> dto.setDiscountPercent(discountDetail.getDiscountPercent()));
            ProductPrice productPrice = productPriceRepo.findByProduct(e.getProductID());
            dto.setPrice(productPrice.getPrice());
            return dto;
        }).collect(Collectors.toList());
        if (sort.equalsIgnoreCase("DESC"))
            return results.stream().sorted(Comparator.comparing(ProductDTO::getPrice).reversed())
                    .collect(Collectors.toList());
        return results.stream().sorted(Comparator.comparing(ProductDTO::getPrice)).collect(Collectors.toList());
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
        return productRepo
                .findByCategory_CategoryIDAndNameLike(categoryID, "%" + value + "%").stream()
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

    private final String EQUAL = " = ";
    private final String LIKE = " LIKE ";
    private final String oriID = " originID" + EQUAL;
    private final String braID = " brandID" + EQUAL;
    private final String skiID = " skinTypeID" + EQUAL;
    private final String tonID = " toneID" + EQUAL;
    private final String ingID = " ingredientID" + EQUAL;
    private final String chaID = " characteristicID" + EQUAL;
    private final String sizID = " sizeID" + EQUAL;
    private final String proName = " otherName" + LIKE;
    private final String catID = " categoryID" + EQUAL;
    private final String WHERE = " WHERE ";
}
