package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.DiscountDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DiscountDetailRepository extends JpaRepository<DiscountDetail, DiscountDetailKey> {
    Optional<DiscountDetail> findTopByProduct_ProductIDAndDiscount_StartTimeLessThanEqualAndDiscount_EndTimeGreaterThanEqual(int productID
            , Date startDate, Date endDate);
}
