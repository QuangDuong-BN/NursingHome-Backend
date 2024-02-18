package com.example.nursinghome.entitydto;

import com.example.nursinghome.enumcustom.TypeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceInfoDTO {
    private String name;
    private TypeService type;
    private Double priceDay;
    private Double priceWeek;
    private Double priceMonth;
    private Double priceYear;
    private Double ticketPrices;
    private String descriptionService;
    private String imageUrl;
}
