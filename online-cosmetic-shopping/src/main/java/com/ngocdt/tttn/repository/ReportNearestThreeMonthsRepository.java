package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ReportNearestThreeMonths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportNearestThreeMonthsRepository extends JpaRepository<ReportNearestThreeMonths, String> {
    @Query(value = "select o.state, count(1) quantity from Orders o where cast(o.date as nchar(7)) = :month and state in ('DELIVERED', 'FAILURE', 'CANCELED') group by o.state", nativeQuery = true)
    List<ReportNearestThreeMonths> getReport(@Param("month") String month);
}
