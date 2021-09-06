package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.NearestThreeMonthsDTO;
import com.ngocdt.tttn.entity.ReportRevenue;
import com.ngocdt.tttn.entity.ReportRevenueByDate;

import java.util.List;

public interface ReportService {
    List<ReportRevenue> getRevenueByDate(String date);
    List<ReportRevenue> getRevenueByMonth(String date);
    List<NearestThreeMonthsDTO> getNearestThreeMonths();
    List<ReportRevenueByDate> getRevenueInMonth(String date);
}
