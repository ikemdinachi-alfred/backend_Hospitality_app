package com.alfredTech.hospitality_management_application.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class UpdateRoomRequest {
    private String description;
    private String roomType;
    private BigDecimal roomPrice;
    private MultipartFile photo;
}
