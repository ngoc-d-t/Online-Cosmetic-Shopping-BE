package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Order;
import com.ngocdt.tttn.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_CustomerID(Integer id);
    List<Order> findAllByCustomer_CustomerIDAndAndState(Integer id , OrderState state);
    List<Order> findAllByShipper_EmployeeID(Integer id);

    @Query(value = "select top 1 f.employeeID from (select A.employeeID, isnull((select quantity from (select count(1) quantity, o.shipperID from Orders o where o.state in ('CONFIRMED', 'DELIVERED','DELIVERING','FAILURE') and ISNULL(o.shipperID, 0) != 0 and CAST(o.date as nchar(7)) = CAST(CAST(getdate() as date) as nchar(7)) group by o.shipperID) s where s.shipperID = a.employeeID), 0) quantity from (select roleID from role where roleName='ROLE_SHIPPER') r INNER JOIN Account a on a.roleID = r.roleID) f order by f.quantity", nativeQuery = true)
    Integer findShipper();

}
