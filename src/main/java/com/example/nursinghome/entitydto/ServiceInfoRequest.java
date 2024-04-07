package com.example.nursinghome.entitydto;

import com.example.nursinghome.enumcustom.TypeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceInfoRequest {
    private String name;
    private TypeService type;
    private Double priceDay;
    private Double priceWeek;
    private Double priceMonth;
    private Double priceYear;
    private Double ticketPrices;
    private String descriptionService;
    private String participantDetails;
    private String amenities;
    private String nutritionMode;
    private String communityActivities;
    private String careRegimen;
    private MultipartFile imageIcon;
    private MultipartFile imagePrice;
}
