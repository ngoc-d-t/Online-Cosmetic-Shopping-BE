package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.*;
import com.ngocdt.tttn.entity.*;
import com.ngocdt.tttn.enums.OrderState;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.exception.NotFoundException;
import com.ngocdt.tttn.repository.*;
import com.ngocdt.tttn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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
    private final EmployeeRepository employeeRepo;
    private final AddressRepository addressRepo;
    private final InvoiceRepository invoiceRepo;

    @Override
    public List<OrderDTO> showAll() {
        List<OrderDTO> result = orderRepo.findAll().stream().map(OrderDTO::toDTO).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
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
        if (accountCurrent != null) {
            order.setCustomer(accountCurrent.getCustomer());
        }
        order.setTotalDiscount(0);
        order.setTotalPrice(0);
        order.setPaid(dto.getPaid());
        order.setTransportationFee(dto.getTransportationFee());
        Address address;
        if (dto.getAddress().getAddressID() > 0) {
            address = addressRepo.findById(dto.getAddress().getAddressID())
                    .orElseThrow(() -> new NotFoundException("Address not found."));
        } else {
            Address ad = new Address();
            ad.setAddressID(0);
            ad.setPhoneNumber(dto.getAddress().getPhoneNumber());
            ad.setName(dto.getAddress().getName());
            ad.setAddress(dto.getAddress().getAddress());
            ad.setCustomer(order.getCustomer());
            address = addressRepo.save(ad);
        }
        order.setAddress(address);
        if (dto.getPaid() != 0)
            order.setState(OrderState.PAID);
        order = orderRepo.save(order);

        List<OrderDetail> details = new ArrayList<>();
        float totalDiscount = 0;
        for (OrderDetailDTO detail : dto.getOrderDetails()) {
            Product product = productRepo.findById(detail.getProduct().getProductID())
                    .orElseThrow(() -> new BadRequestException("Not found product."));
            if (detail.getQuantity() > product.getQuantity())
                throw new BadRequestException("Product is not enough.");
            DiscountDetail discountDetails = discountDetailRepo
                    .findByProduct(detail.getProduct().getProductID()).orElse(null);
            float discount = 0;
            ProductPrice productPrice = productPriceRepo
                    .findByProduct(detail.getProduct().getProductID());
            float price = productPrice.getPrice();
            if (discountDetails != null) {
                discount = discountDetails.getDiscountPercent();
                totalDiscount += (price * detail.getQuantity()) * (discountDetails.getDiscountPercent() / 100f);
            }
            detail.setPrice(price);
            detail.setDiscount(totalDiscount);
            detail.setOrderID(order.getOrderID());
            detail.setProduct(ProductDTO.toDTO(product));
            details.add(OrderDetailDTO.toEntity(createDetail(detail)));

            int sumQuantity = product.getQuantity() - detail.getQuantity();
            product.setQuantity(sumQuantity);
            productRepo.save(product);
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
        Product product = productRepo.findById(dto.getProduct().getProductID())
                .orElseThrow(() -> new NotFoundException("Product not found."));
        OrderDetail od = OrderDetailDTO.toEntity(dto);
        od.setOrderDetailID(0);
        od = orderDetailRepo.save(od);
        od.setProduct(product);
        return OrderDetailDTO.toDTO(od);
    }

    @Override
    public OrderDTO confirm(Integer id, Integer employeeID) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        Employee employee = employeeRepo.findById(employeeID).orElseThrow(() -> new NotFoundException("Employee not found."));
        if (order.getState() != OrderState.UNCONFIRMED && order.getState() != OrderState.PAID)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.CONFIRMED);
        order.setEmployee(employee);
        Employee shipper = employeeRepo.findById(orderRepo.findShipper())
                .orElseThrow(() -> new NotFoundException("No shipper available."));
        order.setShipper(shipper);
        return OrderDTO.toDTO(orderRepo.save(order));
    }

    @Override
    public void delivering(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (order.getState() != OrderState.CONFIRMED)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.DELIVERING);
        orderRepo.save(order);
    }

    @Override
    public void delivered(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (order.getState() != OrderState.DELIVERING)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.DELIVERED);
        order.setPaid(order.getTotalPrice() - order.getTotalDiscount() + order.getTransportationFee());
        Invoice invoice = new Invoice();
        invoice.setEmployee(order.getShipper());
        invoice.setDate(new Date());
        invoice.setOrder(order);
        invoiceRepo.save(invoice);
        orderRepo.save(order);
    }

    @Override
    public void canceled(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (order.getState() == OrderState.CANCELED || order.getState() == OrderState.DELIVERED)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.CANCELED);
        orderRepo.save(order);
        List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrder_OrderID(order.getOrderID());
        for (OrderDetail od : orderDetails
        ) {
            Product p = productRepo.findById(od.getProduct().getProductID()).get();
            p.setQuantity(p.getQuantity() + od.getQuantity());
            productRepo.save(p);
        }
    }

    @Override
    public void failure(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (order.getState() != OrderState.DELIVERING)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.FAILURE);
        orderRepo.save(order);
        List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrder_OrderID(order.getOrderID());
        for (OrderDetail od : orderDetails
        ) {
            Product p = productRepo.findById(od.getProduct().getProductID()).get();
            p.setQuantity(p.getQuantity() + od.getQuantity());
            productRepo.save(p);
        }
    }

    @Override
    public OrderDTO update(OrderDTO dto, Employee employee) {
        if (employee == null)
            throw new BadRequestException("Employee is not exist.");
        Order order = orderRepo.findById(dto.getOrderID()).get();
        if (order == null)
            throw new BadRequestException("Order is not exist.");
        order.setShipper(EmployeeDTO.toEntity(dto.getShipper()));
        return OrderDTO.toDTO(orderRepo.save(order));
    }

    @Override
    public List<OrderDTO> showAllByUser(int accountID) {
        Account account = accountRepo.findById(accountID).get();
        if (account == null)
            throw new BadRequestException("Account is not exists.");
        if (account.getCustomer() == null)
            throw new BadRequestException("Customer is not exists.");
        List<OrderDTO> result = orderRepo.findAllByCustomer_CustomerID(account.getCustomer().getCustomerID())
                .stream().map(OrderDTO::toDTO).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    @Override
    @Transactional
    public void requestCanceled(Integer id, int customerID) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (order.getState() != OrderState.UNCONFIRMED)
            throw new BadRequestException("Can not change state");
        if (order.getCustomer().getCustomerID() != customerID)
            throw new BadRequestException("Can not change state");
        order.setState(OrderState.CANCELED);
        orderRepo.save(order);
        List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrder_OrderID(order.getOrderID());
        for (OrderDetail od : orderDetails
        ) {
            Product p = productRepo.findById(od.getProduct().getProductID()).get();
            p.setQuantity(p.getQuantity() + od.getQuantity());
            productRepo.save(p);
        }
    }

    @Override
    public List<OrderDTO> showAllByUserAndState(OrderState state, int accountID) {
        Account account = accountRepo.findById(accountID).get();
        if (account == null)
            throw new BadRequestException("Account is not exists.");
        if (account.getCustomer() == null)
            throw new BadRequestException("Customer is not exists.");
        List<OrderDTO> result = orderRepo.findAllByCustomer_CustomerIDAndAndState(account.getCustomer().getCustomerID(), state)
                .stream().map(OrderDTO::toDTO).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<OrderDTO> showAllByShipper(Integer id) {
        List<OrderDTO> result = orderRepo.findAllByShipper_EmployeeID(id).stream().map(OrderDTO::toDTO).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

}
