package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.DiscountDTO;
import com.ngocdt.tttn.dto.DiscountDetailDTO;
import com.ngocdt.tttn.entity.*;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Transactional
    public DiscountDTO update(DiscountDTO dto, HttpServletRequest request) {
        if (!discountRepo.existsById(dto.getDiscountID()))
            throw new BadRequestException("Bad request.");
        Discount discount = discountRepo.findById(dto.getDiscountID())
                .orElseThrow(()-> new NotFoundException("Discount not found."));
        discount.setName(dto.getName());
        discount.setEndTime(dto.getEndTime());
        discount.setStartTime(dto.getStartTime());
        for (DiscountDetailDTO detail: dto.getDiscountDetails()) {
            discountDetailRepo.save(DiscountDetailDTO.toEntity(detail));
        }
        return DiscountDTO.toDTO(discountRepo.save(discount));
    }

    @Override
    @Transactional
    public DiscountDTO create(DiscountDTO dto, HttpServletRequest request) {
        Discount discount = DiscountDTO.toEntity(dto);
        discount.setDiscountID(0);
        Account currentAccount = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        discount = discountRepo.save(discount);
        List<DiscountDetailDTO> discounts = new ArrayList<>();
        for (DiscountDetailDTO detail : dto.getDiscountDetails()
        ) {
            detail.setDiscountID(discount.getDiscountID());
            discounts.add(createDetail(detail));
        }
        DiscountDTO discountDTO = DiscountDTO.toDTO(discount);
        discountDTO.setDiscountDetails(discounts);
        return discountDTO;
    }

    @Override
    public void delete(Integer id) {
        if (!discountRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        discountRepo.deleteById(id);
    }

    @Override
    public DiscountDetailDTO createDetail(DiscountDetailDTO dto) {
        if(!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        DiscountDetail dd = DiscountDetailDTO.toEntity(dto);
        return DiscountDetailDTO.toDTO(discountDetailRepo.save(dd));
    }

    @Override
    public DiscountDetailDTO updateDetail(DiscountDetailDTO dto) {
        DiscountDetailKey key = new DiscountDetailKey();
        key.setDiscountID(dto.getDiscountID());
        key.setProductID(dto.getProductID());
        if (!discountDetailRepo.existsById(key))
            throw new BadRequestException("Discount detail not found.");
        DiscountDetail dd = DiscountDetailDTO.toEntity(dto);
        return DiscountDetailDTO.toDTO(discountDetailRepo.save(dd));
    }
}
