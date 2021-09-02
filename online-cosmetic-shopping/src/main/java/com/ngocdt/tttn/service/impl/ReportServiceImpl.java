package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.entity.ReportRevenue;
import com.ngocdt.tttn.repository.ReportRevenueRepository;
import com.ngocdt.tttn.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRevenueRepository reportRevenueRepo;
    @Override
    public List<ReportRevenue> getRevenueByDate(String date) {
        return reportRevenueRepo.getReportRevenueByDate(date);
    }

    @Override
    public List<ReportRevenue> getRevenueByMonth(String date) {
        return reportRevenueRepo.getReportRevenueByMonth(date);
    }
}
