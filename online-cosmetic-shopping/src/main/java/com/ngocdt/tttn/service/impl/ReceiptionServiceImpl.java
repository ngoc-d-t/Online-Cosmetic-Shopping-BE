package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.ReceiptionDTO;
import com.ngocdt.tttn.dto.ReceiptionDetailDTO;
import com.ngocdt.tttn.dto.ReceiptionDetailKeyDTO;
import com.ngocdt.tttn.entity.Receiption;
import com.ngocdt.tttn.entity.ReceiptionDetail;
import com.ngocdt.tttn.entity.ReceiptionDetailKey;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.ReceiptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReceiptionServiceImpl implements ReceiptionService {
    private final ReceiptionRepository receiptionRepo;
    private final ReceiptionDetailRepository receiptionDetailRepo;
    private final OrderForSupplierRepository orderForSupplierRepo;
    private final EmployeeRepository employeeRepo;
    private final ProductRepository productRepo;

    @Override
    public List<ReceiptionDTO> showAll() {
        return receiptionRepo.findAll().stream().map(ReceiptionDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReceiptionDTO showOne(Integer id) {
        return ReceiptionDTO.toDTO(receiptionRepo.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ReceiptionDTO update(ReceiptionDTO dto) {
        if (!receiptionRepo.existsById(dto.getReceiptionID()))
            throw new BadRequestException("Receiption not found.");
        if (!orderForSupplierRepo.existsById(dto.getOrderForSupplierID()))
            throw new BadRequestException("Order for supplier not found.");
        if (!employeeRepo.existsById(dto.getEmployeeID()))
            throw new BadRequestException("Employee not found");
        Receiption receiption = ReceiptionDTO.toEntity(dto);
        ReceiptionDTO receiptionDTO = ReceiptionDTO.toDTO(receiptionRepo.save(receiption));
        List<ReceiptionDetailDTO> detailDTOS = new ArrayList<>();
        for (ReceiptionDetailDTO detail : dto.getReceiptionDetails()
        ) {
            detail.setReceiptionID(receiptionDTO.getReceiptionID());
            detailDTOS.add(updateDetail(detail));
        }
        receiptionDTO.setReceiptionDetails(detailDTOS);
        return receiptionDTO;
    }

    @Override
    @Transactional
    public ReceiptionDTO create(ReceiptionDTO dto) {
        if (!orderForSupplierRepo.existsById(dto.getOrderForSupplierID()))
            throw new BadRequestException("Order for supplier not found.");
        if (!employeeRepo.existsById(dto.getEmployeeID()))
            throw new BadRequestException("Employee not found");
        Receiption receiption = ReceiptionDTO.toEntity(dto);
        receiption.setReceiptionID(0);
        ReceiptionDTO receiptionDTO = ReceiptionDTO.toDTO(receiptionRepo.save(receiption));
        List<ReceiptionDetailDTO> detailDTOS = new ArrayList<>();
        for (ReceiptionDetailDTO detail : dto.getReceiptionDetails()
        ) {
            detail.setReceiptionID(receiptionDTO.getReceiptionID());
            detailDTOS.add(updateDetail(detail));
        }
        receiptionDTO.setReceiptionDetails(detailDTOS);
        return receiptionDTO;
    }

    @Override
    public void delete(Integer id) {
        if (!receiptionRepo.existsById(id))
            throw new BadRequestException("Receiption not found.");
        receiptionRepo.deleteById(id);
    }

    @Override
    public ReceiptionDetailDTO createDetail(ReceiptionDetailDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        ReceiptionDetail detail = ReceiptionDetailDTO.toEntity(dto);
        return ReceiptionDetailDTO.toDTO(receiptionDetailRepo.save(detail));
    }

    @Override
    public ReceiptionDetailDTO updateDetail(ReceiptionDetailDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        ReceiptionDetail detail = ReceiptionDetailDTO.toEntity(dto);
        return ReceiptionDetailDTO.toDTO(receiptionDetailRepo.save(detail));
    }

    @Override
    public void deleteDetail(ReceiptionDetailKeyDTO id) {
        ReceiptionDetailKey key = ReceiptionDetailKeyDTO.toEntity(id);
        if (!receiptionDetailRepo.existsById(key))
            throw new BadRequestException("Receiption detail not found.");
        receiptionDetailRepo.deleteById(key);
    }
}
