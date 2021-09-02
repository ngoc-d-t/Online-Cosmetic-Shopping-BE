package com.ngocdt.tttn.service;

import com.ngocdt.tttn.entity.ReportRevenue;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReportService {
    List<ReportRevenue> getRevenueByDate(String date);
    List<ReportRevenue> getRevenueByMonth(String date);
}
