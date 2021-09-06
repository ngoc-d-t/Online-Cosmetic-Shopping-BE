package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.ReportNearestThreeMonths;

import java.util.List;

public class NearestThreeMonthsDTO {
    private String month;
    private List<ReportNearestThreeMonths> nearestThreeMonths;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<ReportNearestThreeMonths> getNearestThreeMonths() {
        return nearestThreeMonths;
    }

    public void setNearestThreeMonths(List<ReportNearestThreeMonths> nearestThreeMonths) {
        this.nearestThreeMonths = nearestThreeMonths;
    }
}
