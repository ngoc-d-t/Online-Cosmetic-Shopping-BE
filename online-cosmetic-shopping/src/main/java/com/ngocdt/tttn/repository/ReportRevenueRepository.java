package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ReportRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRevenueRepository extends JpaRepository<ReportRevenue, Integer> {
    @Query(value = "select (select name from Product p where p.productID = od.productID) name, " +
            "sum(od.quantity) quantity, sum(od.price * (1 - od.discount/100) * od.quantity) totalPrice, " +
            "od.productID id from (select * from OrderDetail od1) od inner join " +
            "(select o1.orderID from Orders o1 where o1.date = :date and o1.state = 'DELIVERED') o " +
            "on od.orderID = o.orderID group by od.productID", nativeQuery = true)
    List<ReportRevenue> getReportRevenueByDate(String date);

    @Query(value = "select (select name from Product p where p.productID = od.productID) name, " +
            "sum(od.quantity) quantity, sum(od.price * (1 - od.discount/100) * od.quantity) totalPrice, " +
            "od.productID id from (select * from OrderDetail od1) od inner join " +
            "(select o1.orderID from Orders o1 where CAST(o1.date as nchar(7)) = :date and CAST(o1.date as nchar(7)) = :date " +
            "and o1.state = 'DELIVERED') o on od.orderID = o.orderID group by od.productID", nativeQuery = true)
    List<ReportRevenue> getReportRevenueByMonth(String date);
}
