package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.OrderDTO;
import com.ngocdt.tttn.dto.OrderDetailDTO;
import com.ngocdt.tttn.dto.ProductDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.Order;
import com.ngocdt.tttn.entity.OrderDetail;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final AccountRepository accountRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final ProductRepository productRepo;
    private final ProductPriceRepository productPriceRepo;
    private final DiscountDetailRepository discountDetailRepo;

    @Override
    public List<OrderDTO> showAll() {
        return orderRepo.findAll().stream().map(OrderDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO showOne(Integer id) {
        return OrderDTO.toDTO(orderRepo.findById(id).orElse(null));
    }


    @Override
    @Transactional
    public OrderDTO create(OrderDTO dto, HttpServletRequest request) {
        Order order = new Order();
        Account accountCurrent = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        order.setCustomer(accountCurrent.getCustomer());
        order.setTotalDiscount(0);
        order.setTotalPrice(0);
        order = orderRepo.save(order);
        List<OrderDetail> details = new ArrayList<>();
        float totalDiscount = 0;
        for (OrderDetailDTO detail : dto.getOrderDetails()) {
            ProductDTO productDTO = ProductDTO.toDTO(productRepo.findById(detail.getProductID()).orElseThrow(() -> new BadRequestException("Not found product.")));
            if(detail.getQuantity()> productDTO.getQuantity())
                throw new BadRequestException("Product is not enough.");
            DiscountDetail discountDetails = discountDetailRepo
                    .findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(
                            detail.getProductID(), new Date(), new Date()).orElse(null);
            float discount = 0;
            float productPrice = productPriceRepo.findTop1ByDateLessThanEqual(new Date()).getPrice();
            if(discountDetails!=null){
                discount = discountDetails.getDiscountPercent();
                totalDiscount += discountDetails.getDiscountPercent() * productPrice * detail.getQuantity();
            }
            detail.setPrice(productPrice);
            detail.setDiscount(discount);
            detail.setOrderID(order.getOrderID());
            details.add(OrderDetailDTO.toEntity(createDetail(detail)));
            int sumQuantity = productDTO.getQuantity() - detail.getQuantity();
            productDTO.setQuantity(sumQuantity);
            productRepo.save(ProductDTO.toEntity(productDTO));
        }
        float totalPrice = orderDetailRepo.sumPaymentByOrderID(order.getOrderID());
        order.setOrderDetails(details);
        order.setTotalPrice(totalPrice);
        order.setTotalDiscount(totalDiscount);
        return OrderDTO.toDTO(orderRepo.save(order));
    }

    @Override
    public void delete(Integer id) {
        if (!orderRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        orderRepo.deleteById(id);
    }

    @Override
    public OrderDetailDTO createDetail(OrderDetailDTO dto) {
        if (!productRepo.existsById(dto.getProductID())) {
            throw new BadRequestException("Product not found.");
        }
        OrderDetail od = OrderDetailDTO.toEntity(dto);
        od.setOrderDetailID(0);
        return OrderDetailDTO.toDTO(orderDetailRepo.save(od));
    }

}
