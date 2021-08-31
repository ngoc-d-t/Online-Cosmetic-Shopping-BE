package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.OrderDTO;
import com.ngocdt.tttn.dto.OrderDetailDTO;
import com.ngocdt.tttn.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    List<OrderDTO> showAll();

    OrderDTO showOne(Integer id);

    OrderDTO create(OrderDTO dto, HttpServletRequest request);

    void delete(Integer id);

    OrderDetailDTO createDetail(OrderDetailDTO dto);

    void confirm(Integer id);

    void delivering(Integer id);

    void delivered(Integer id);

    OrderDTO update(OrderDTO dto, Employee employee);

    void canceled(Integer id);

    List<OrderDTO> showAllByUser(int accountID);
}
