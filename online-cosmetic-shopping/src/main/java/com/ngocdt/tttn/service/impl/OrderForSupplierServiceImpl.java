package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.OrderDetailForSupplierDTO;
import com.ngocdt.tttn.dto.OrderDetailForSupplierKeyDTO;
import com.ngocdt.tttn.dto.OrderForSupplierDTO;
import com.ngocdt.tttn.entity.*;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.OrderForSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderForSupplierServiceImpl implements OrderForSupplierService {
    private final OrderForSupplierRepository orderForSupplierRepo;
    private final OrderDetailForSupplierRepository orderDetailForSupplierRepo;
    private final SupplierRepository supplierRepo;
    private final ProductRepository productRepo;
    private final AccountRepository accountRepo;

    @Override
    public List<OrderForSupplierDTO> showAll() {
        return orderForSupplierRepo.findAll().stream().map(OrderForSupplierDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderForSupplierDTO showOne(Integer id) {
        return OrderForSupplierDTO.toDTO(orderForSupplierRepo.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public OrderForSupplierDTO update(OrderForSupplierDTO dto) {
        if (!orderForSupplierRepo.existsById(dto.getOrderForSupplierID()))
            throw new BadRequestException("Bad request.");
        List<OrderDetailForSupplier> responDetails = new ArrayList<>();
        for (OrderDetailForSupplierDTO detail :
                dto.getOrderDetailForSuppliers()) {
            if (dto.getOrderForSupplierID() != detail.getOrderForSupplierID())
                throw new BadRequestException("Order not found.");
            responDetails.add(OrderDetailForSupplierDTO.toEntity(updateDetail(detail)));
        }
        OrderForSupplier od = OrderForSupplierDTO.toEntity(dto);
        od.setOrderDetailForSuppliers(responDetails);
        od.setTotalPayment(orderDetailForSupplierRepo.sumPaymentByOrderForSupplierID(od.getOrderForSupplierID()));
        return OrderForSupplierDTO.toDTO(orderForSupplierRepo.save(od));
    }

    @Override
    @Transactional
    public OrderForSupplierDTO create(OrderForSupplierDTO dto, HttpServletRequest request) {
        if (!supplierRepo.existsById(dto.getSupplierID()))
            throw new BadRequestException("Supplier not found.");
        OrderForSupplier od = OrderForSupplierDTO.toEntity(dto);
        od.setOrderForSupplierID(0);
        Account accountCurrent = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        od.setEmployee(accountCurrent.getEmployee());
        OrderForSupplierDTO responDTO = OrderForSupplierDTO.toDTO(orderForSupplierRepo.save(od));
        if (responDTO == null)
            return null;
        List<OrderDetailForSupplierDTO> responDetails = new ArrayList<>();
        for (OrderDetailForSupplierDTO detail :
                dto.getOrderDetailForSuppliers()) {
            detail.setOrderForSupplierID(responDTO.getOrderForSupplierID());
            responDetails.add(createDetail(detail));
        }
        long totalPayment = orderDetailForSupplierRepo.sumPaymentByOrderForSupplierID(responDTO.getOrderForSupplierID());
        responDTO.setOrderDetailForSuppliers(responDetails);
        od = OrderForSupplierDTO.toEntity(responDTO);
        od.setTotalPayment(totalPayment);
        return OrderForSupplierDTO.toDTO(orderForSupplierRepo.save(od));
    }

    @Override
    public OrderDetailForSupplierDTO createDetail(OrderDetailForSupplierDTO dto) {
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        OrderDetailForSupplier od = OrderDetailForSupplierDTO.toEntity(dto);
        return OrderDetailForSupplierDTO.toDTO(orderDetailForSupplierRepo.save(od));
    }


    @Override
    public void delete(Integer id) {
        if (!orderForSupplierRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        orderForSupplierRepo.deleteById(id);
    }

    @Override
    public OrderDetailForSupplierDTO updateDetail(OrderDetailForSupplierDTO dto) {
        OrderDetailForSupplierKey key = new OrderDetailForSupplierKey();
        key.setOrderForSupplierID(dto.getOrderForSupplierID());
        key.setProductID(dto.getProductID());
        if (!orderDetailForSupplierRepo.existsById(key))
            throw new BadRequestException("Order detail not found.");
        if (!productRepo.existsById(dto.getProductID()))
            throw new BadRequestException("Product not found.");
        OrderDetailForSupplier od = OrderDetailForSupplierDTO.toEntity(dto);
        return OrderDetailForSupplierDTO.toDTO(orderDetailForSupplierRepo.save(od));
    }

    @Override
    public void deleteDetail(OrderDetailForSupplierKeyDTO id) {
        OrderDetailForSupplierKey key = OrderDetailForSupplierKeyDTO.toEntity(id);
        if (!orderDetailForSupplierRepo.existsById(key))
            throw new BadRequestException("Bad request.");
        orderDetailForSupplierRepo.deleteById(key);
    }
}
