package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.DiscountDTO;
import com.ngocdt.tttn.dto.DiscountDetailDTO;
import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.entity.Discount;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.DiscountDetailKey;
import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.exception.NotFoundException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.DiscountDetailRepository;
import com.ngocdt.tttn.repository.DiscountRepository;
import com.ngocdt.tttn.repository.ProductRepository;
import com.ngocdt.tttn.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepo;
    private final DiscountDetailRepository discountDetailRepo;
    private final AccountRepository accountRepo;
    private final ProductRepository productRepo;

    @Override
    public List<DiscountDTO> showAll() {
        return discountRepo.findAll().stream().map(DiscountDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public DiscountDTO showOne(Integer id) {
        return DiscountDTO.toDTO(discountRepo.findById(id).orElse(null));
    }

    @Override
    public DiscountDTO update(DiscountDTO dto) {
        if (!discountRepo.existsById(dto.getDiscountID()))
            throw new BadRequestException("Bad request.");
        Discount discount = discountRepo.findById(dto.getDiscountID())
                .orElseThrow(() -> new NotFoundException("Discount not found."));
        discount.setName(dto.getName());
        discount.setEndTime(dto.getEndTime());
        discount.setStartTime(dto.getStartTime());
        return DiscountDTO.toDTO(discountRepo.save(discount));
    }

    @Override
    @Transactional
    public DiscountDTO create(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setEndTime(dto.getEndTime());
        discount.setStartTime(dto.getStartTime());
        discount.setName(dto.getName());
        discount.setDiscountID(0);
        ;
        return DiscountDTO.toDTO(discountRepo.save(discount));
    }

    @Override
    public void delete(Integer id) {
        if (!discountRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        discountRepo.deleteById(id);
    }

    @Override
    public DiscountDetailDTO createDetail(DiscountDetailDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        List<Product> products = productRepo.findAllByDiscount();
        Discount d = discountRepo.findById(dto.getDiscountID()).orElseThrow(()-> new NotFoundException("Discount not found."));
        for (Product p: products) {
            if(p.getProductID() == dto.getProductID()){
                DiscountDetail discountDetail = discountDetailRepo
                        .findByProductIDAndTime(dto.getProductID(),d.getStartTime()).orElse(null);
                if(discountDetail != null)
                    throw new BadRequestException("Can not create discount at now.");
            }
        }
        DiscountDetail dd = DiscountDetailDTO.toEntity(dto);
        return DiscountDetailDTO.toDTO(discountDetailRepo.save(dd));
    }

    @Override
    public DiscountDetailDTO updateDetail(DiscountDetailDTO dto) {
        DiscountDetailKey key = new DiscountDetailKey();
        key.setDiscountID(dto.getDiscountID());
        key.setProductID(dto.getProductID());
        DiscountDetail dd = discountDetailRepo.findById(key).orElse(null);
        if (dd == null)
            throw new BadRequestException("Discount detail not found.");
        if (dto.getDiscountPercent() == 0){
            discountDetailRepo.delete(dd);
            return null;
        }
        dd.setDiscountPercent(dto.getDiscountPercent());
        return DiscountDetailDTO.toDTO(discountDetailRepo.save(dd));
    }
}
