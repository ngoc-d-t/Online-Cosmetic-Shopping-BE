package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.OrderDetailForSupplierDTO;
import com.ngocdt.tttn.dto.OrderDetailForSupplierKeyDTO;
import com.ngocdt.tttn.dto.OrderForSupplierDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderForSupplierService {
    List<OrderForSupplierDTO> showAll();

    OrderForSupplierDTO showOne(Integer id);

    OrderForSupplierDTO update(OrderForSupplierDTO dto);

    OrderForSupplierDTO create(OrderForSupplierDTO dto, HttpServletRequest request);

    void delete(Integer id);

    OrderDetailForSupplierDTO createDetail(OrderDetailForSupplierDTO dto);

    OrderDetailForSupplierDTO updateDetail(OrderDetailForSupplierDTO dto);

    void deleteDetail(OrderDetailForSupplierKeyDTO id);
}
