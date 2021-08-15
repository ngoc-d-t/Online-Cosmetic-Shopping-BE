package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ReceiptionDetail;
import com.ngocdt.tttn.entity.ReceiptionDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptionDetailRepository extends JpaRepository<ReceiptionDetail, ReceiptionDetailKey> {
}
