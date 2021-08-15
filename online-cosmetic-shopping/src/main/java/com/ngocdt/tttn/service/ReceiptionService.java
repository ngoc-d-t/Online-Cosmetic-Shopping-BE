package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.ReceiptionDTO;
import com.ngocdt.tttn.dto.ReceiptionDetailDTO;
import com.ngocdt.tttn.dto.ReceiptionDetailKeyDTO;
import com.ngocdt.tttn.entity.ReceiptionDetail;
import com.ngocdt.tttn.entity.ReceiptionDetailKey;

public interface ReceiptionService extends GenericService<ReceiptionDTO, Integer> {
    ReceiptionDetailDTO createDetail(ReceiptionDetailDTO dto);
    ReceiptionDetailDTO updateDetail(ReceiptionDetailDTO dto);
    void deleteDetail(ReceiptionDetailKeyDTO id);
}
