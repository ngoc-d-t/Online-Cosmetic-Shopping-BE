package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.DiscountDTO;
import com.ngocdt.tttn.dto.DiscountDetailDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DiscountService {
    List<DiscountDTO> showAll();

    DiscountDTO showOne(Integer id);

    DiscountDTO create(DiscountDTO dto);

    DiscountDTO update(DiscountDTO dto);

    void delete(Integer id);

    DiscountDetailDTO createDetail(DiscountDetailDTO dto);

    DiscountDetailDTO updateDetail(DiscountDetailDTO dto);
}
