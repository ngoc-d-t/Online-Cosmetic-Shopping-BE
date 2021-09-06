package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ReportRevenueByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRevenueByDateRepository extends JpaRepository<ReportRevenueByDate, String> {
    @Query(value="select i.date, sum(o.totalPrice) revenue from (select i1.orderID, i1.date from Invoice i1 where i1.date between :begin and :end) i inner join (select * from Orders o1 where o1.state = 'DELIVERED') o on o.orderID = i.orderID group by i.date",nativeQuery=true)
    List<ReportRevenueByDate> getReport(@Param("begin") String begin, @Param("end") String end);
}
