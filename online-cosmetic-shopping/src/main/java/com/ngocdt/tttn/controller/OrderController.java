package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.OrderDTO;
import com.ngocdt.tttn.enums.OrderState;
import com.ngocdt.tttn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/user/orders/{id}")
    public ResponseEntity<OrderDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(orderService.showOne(id));
    }

    @PostMapping("/public/orders/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(orderService.create(dto, request));
    }

    @PatchMapping("/admin/orders/{id}/{state}")
    public ResponseEntity<Void> changeState(@PathVariable("id") Integer id,
                                            @PathVariable("state") OrderState state) {
        if (state == OrderState.CONFIRMED) {
            orderService.confirm(id);
            return ResponseEntity.ok().build();
        } else if (state == OrderState.DELIVERING) {
            orderService.delivering(id);
            return ResponseEntity.ok().build();
        } else if (state == OrderState.DELIVERED) {
            orderService.delivered(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/admin/orders")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
