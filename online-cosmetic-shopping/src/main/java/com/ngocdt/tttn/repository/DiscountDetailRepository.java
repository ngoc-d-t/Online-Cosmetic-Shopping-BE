package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.DiscountDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface DiscountDetailRepository extends JpaRepository<DiscountDetail, DiscountDetailKey> {
    @Query(value = "select a.discountID, a.discountPercent, a.productID from (select * from DiscountDetail dd " +
            "where dd.productID = :id) a inner join (select d.discountID from Discount d " +
            "where d.startTime <= CAST(getdate() as date) and d.endTime >= CAST(getdate() as date)) b " +
            "on a.discountID = b.discountID", nativeQuery = true)
    Optional<DiscountDetail> findByProduct(@Param("id") int productID);

    @Query(value = "select a.discountID, a.discountPercent, a.productID from (select * from DiscountDetail dd " +
            "where dd.productID = :id) a inner join (select d.discountID from Discount d " +
            "where d.startTime <= CAST(:startTime as date) and d.endTime >= CAST(:startTime as date)) b " +
            "on a.discountID = b.discountID", nativeQuery = true)
    Optional<DiscountDetail> findByProductIDAndTime(@Param("id") int productID, @Param("startTime") Date startTime);
}
