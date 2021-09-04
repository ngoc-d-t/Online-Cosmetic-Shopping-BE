package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.OrderDTO;
import com.ngocdt.tttn.enums.OrderState;
import com.ngocdt.tttn.enums.ROLE;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.security.service.UserDetailsImpl;
import com.ngocdt.tttn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderDTO>> showAll() {
        return ResponseEntity.ok().body(orderService.showAll());
    }

    @GetMapping("/shipper/orders")
    public ResponseEntity<List<OrderDTO>> showAllByShipper() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (userDetails.getRole().getRoleName().equals(ROLE.ROLE_SHIPPER))
            return ResponseEntity.ok().body(orderService.showAllByShipper(userDetails.getEmployee().getEmployeeID()));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user/orders/{id}")
    public ResponseEntity<OrderDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(orderService.showOne(id));
    }

    @GetMapping("/client/orders")
    public ResponseEntity<List<OrderDTO>> showByAllByUser(
            @RequestParam(value = "state", required = false, defaultValue = "") OrderState state) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (state != null && !state.toString().isEmpty()) {
            return ResponseEntity.ok().body(orderService.showAllByUserAndState(state, userDetails.getAccountID()));
        }
        return ResponseEntity.ok().body(orderService.showAllByUser(userDetails.getAccountID()));
    }

    @PatchMapping("/client/orders/{id}")
    public ResponseEntity<Void> requestChangeState(@PathVariable("id") Integer id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (userDetails.getCustomer() == null)
            throw new BadRequestException("Account is wrong");
        orderService.requestCanceled(id, userDetails.getCustomer().getCustomerID());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/client/orders/create")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(orderService.create(dto, request));
    }

    @PatchMapping("/shipper/orders/{id}/{state}")
    public ResponseEntity<OrderDTO> changeState(@PathVariable("id") Integer id,
                                            @PathVariable("state") OrderState state) {

        if (state == OrderState.CONFIRMED) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (userDetails.getEmployee() == null)
                throw new BadRequestException("Employee is not exist.");
           // orderService.confirm(id, userDetails.getEmployee().getEmployeeID());
            return ResponseEntity.ok().body(orderService.confirm(id, userDetails.getEmployee().getEmployeeID()));
        } else if (state == OrderState.DELIVERING) {
            orderService.delivering(id);
            return ResponseEntity.ok().build();
        } else if (state == OrderState.DELIVERED) {
            orderService.delivered(id);
            return ResponseEntity.ok().build();
        } else if (state == OrderState.CANCELED) {
            orderService.canceled(id);
            return ResponseEntity.ok().build();
        }else if (state == OrderState.FAILURE) {
            orderService.failure(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/admin/orders")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/orders")
    public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return ResponseEntity.ok().body(orderService.update(dto, userDetails.getEmployee()));
    }
}
