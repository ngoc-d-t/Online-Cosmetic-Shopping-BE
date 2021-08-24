package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.OrderDTO;
import com.ngocdt.tttn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> showAll() {
        return ResponseEntity.ok().body(orderService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> showOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(orderService.showOne(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(orderService.create(dto, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
