package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.Discount;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class DiscountDTO {
    private int discountID;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date endTime;
    private List<DiscountDetailDTO> discountDetails;

    public static DiscountDTO toDTO(Discount discount) {
        if (discount == null)
            return null;
        DiscountDTO dto = new DiscountDTO();
        dto.setDiscountID(discount.getDiscountID());
        dto.setName(discount.getName());
        dto.setStartTime(discount.getStartTime());
        dto.setEndTime(discount.getEndTime());
        List<DiscountDetailDTO> discountDetails = new ArrayList<>();
        if (discount.getDiscountDetails().size() > 0) {
            for (DiscountDetail detail : discount.getDiscountDetails()) {
                discountDetails.add(DiscountDetailDTO.toDTO(detail));
            }
        }

        dto.setDiscountDetails(discountDetails);

        return dto;
    }

    public static Discount toEntity(DiscountDTO dto) {
        if (dto == null)
            return null;
        Discount discount = new Discount();
        discount.setDiscountID(dto.getDiscountID());
        discount.setName(dto.getName());
        discount.setStartTime(dto.getStartTime());
        discount.setEndTime(dto.getEndTime());
        Employee employee = new Employee();
        List<DiscountDetail> discountDetails = new ArrayList<>();
        for (DiscountDetailDTO detail : dto.getDiscountDetails()) {
            discountDetails.add(DiscountDetailDTO.toEntity(detail));
        }
        discount.setDiscountDetails(discountDetails);
        return discount;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<DiscountDetailDTO> getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(List<DiscountDetailDTO> discountDetails) {
        this.discountDetails = discountDetails;
    }
}
