package com.ngocdt.tttn.controller;


import com.ngocdt.tttn.dto.NearestThreeMonthsDTO;
import com.ngocdt.tttn.entity.ReportRevenue;
import com.ngocdt.tttn.entity.ReportRevenueByDate;
import com.ngocdt.tttn.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/revenue/day")
    public ResponseEntity<List<ReportRevenue>> getRevenueByDay(
            @RequestParam("date") String date) {
        return ResponseEntity.ok().body(reportService.getRevenueByDate(date));
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<List<ReportRevenue>> getRevenueByMonth(
            @RequestParam("date") String date) {
        return ResponseEntity.ok().body(reportService.getRevenueByMonth(date));
    }
    @GetMapping("/nearest/month")
    public ResponseEntity<List<NearestThreeMonthsDTO>> getNearestThreeMonths(){
        return ResponseEntity.ok().body(reportService.getNearestThreeMonths());
    }
    @GetMapping("/revenue")
    public ResponseEntity<List<ReportRevenueByDate>> getRevenueInMonth(@RequestParam("month") String month){
        return ResponseEntity.ok().body(reportService.getRevenueInMonth(month));
    }
}
