package com.ngocdt.tttn.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.ReportRevenue;
import com.ngocdt.tttn.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/revenue/day")
    public ResponseEntity<List<ReportRevenue>> getRevenueByDay(
            @RequestParam("date") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") String date) {
        return ResponseEntity.ok().body(reportService.getRevenueByDate(date));
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<List<ReportRevenue>> getRevenueByMonth(
            @RequestParam("date") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM") String date) {
        return ResponseEntity.ok().body(reportService.getRevenueByMonth(date));
    }
}
