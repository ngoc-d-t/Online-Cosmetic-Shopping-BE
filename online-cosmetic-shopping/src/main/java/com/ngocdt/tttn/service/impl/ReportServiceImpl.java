package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.NearestThreeMonthsDTO;
import com.ngocdt.tttn.entity.ReportNearestThreeMonths;
import com.ngocdt.tttn.entity.ReportRevenue;
import com.ngocdt.tttn.entity.ReportRevenueByDate;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.ReportNearestThreeMonthsRepository;
import com.ngocdt.tttn.repository.ReportRevenueByDateRepository;
import com.ngocdt.tttn.repository.ReportRevenueRepository;
import com.ngocdt.tttn.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRevenueRepository reportRevenueRepo;
    private final ReportNearestThreeMonthsRepository reportNearestThreeMonthsRepo;
    private final ReportRevenueByDateRepository reportRevenueByDateRepo;

    @Override
    public List<ReportRevenue> getRevenueByDate(String date) {
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date))
            throw new BadRequestException("Date invalid");
        return reportRevenueRepo.getReportRevenueByDate(date);
    }

    @Override
    public List<ReportRevenue> getRevenueByMonth(String date) {
        if (!Pattern.matches("^\\d{4}-\\d{2}$", date))
            throw new BadRequestException("Date invalid");
        return reportRevenueRepo.getReportRevenueByMonth(date);
    }

    @Override
    public List<NearestThreeMonthsDTO> getNearestThreeMonths() {
        List<NearestThreeMonthsDTO> lists = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        NearestThreeMonthsDTO dto = null;
        String date = null;

        dto = new NearestThreeMonthsDTO();
        cal.add(Calendar.MONTH, -1);
        date = cal.get(Calendar.YEAR) + "-"
                + ((cal.get(Calendar.MONTH) + 1) < 10 ? ("0" + (cal.get(Calendar.MONTH) + 1)) : (cal.get(Calendar.MONTH) + 1));
        dto.setMonth(date);
        dto.setNearestThreeMonths(reportNearestThreeMonthsRepo.getReport(date));
        lists.add(0, dto);

        dto = new NearestThreeMonthsDTO();
        cal.add(Calendar.MONTH, -1);
        date = cal.get(Calendar.YEAR) + "-"
                + ((cal.get(Calendar.MONTH) + 1) < 10 ? ("0" + (cal.get(Calendar.MONTH) + 1)) : (cal.get(Calendar.MONTH) + 1));
        dto.setMonth(date);
        dto.setNearestThreeMonths(reportNearestThreeMonthsRepo.getReport(date));
        lists.add(0, dto);

        dto = new NearestThreeMonthsDTO();
        cal.add(Calendar.MONTH, -1);
        date = cal.get(Calendar.YEAR) + "-"
                + ((cal.get(Calendar.MONTH) + 1) < 10 ? ("0" + (cal.get(Calendar.MONTH) + 1)) : (cal.get(Calendar.MONTH) + 1));
        dto.setMonth(date);
        dto.setNearestThreeMonths(reportNearestThreeMonthsRepo.getReport(date));
        lists.add(0, dto);
        return lists;
    }

    @Override
    public List<ReportRevenueByDate> getRevenueInMonth(String date) {
        if (!Pattern.matches("^\\d{4}-\\d{2}$", date))
            throw new BadRequestException("Date invalid");
        Integer year = Integer.parseInt(date.split("-")[0]);
        Integer month = Integer.parseInt(date.split("-")[1]);
        List<ReportRevenueByDate> result = new ArrayList<>();
        LocalDate nextMonth = LocalDate.of(year, month, 1).plusMonths(1);
        for (LocalDate d = LocalDate.of(year, month, 1); d.isBefore(nextMonth); d = d.plusDays(1)) {
            ReportRevenueByDate revenue = new ReportRevenueByDate();
            revenue.setDate(d.toString());
            result.add(revenue);
        }
        System.out.println(reportRevenueByDateRepo.getReport(result.get(0).getDate(), result.get(result.size() - 1).getDate()).size());
        reportRevenueByDateRepo.getReport(result.get(0).getDate(), result.get(result.size() - 1).getDate()).forEach(e -> {
            for (ReportRevenueByDate r : result) {
                if (e.getDate().equals(r.getDate()))
                    r.setRevenue(e.getRevenue());
            }
        });
        return result;
    }
}
