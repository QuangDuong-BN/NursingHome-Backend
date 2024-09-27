package com.example.nursinghome.service;

import com.example.nursinghome.model.CostsIncurred;
import com.example.nursinghome.entitydto.CostsIncurredDTO;
import com.example.nursinghome.repository.CostsIncurredRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CostsIncurredService {
    private final CostsIncurredRepository costsIncurredService;
    private final UserRepository userRepository;

    public void addCostsIncurred(HttpServletRequest httpServletRequest, CostsIncurredDTO costsIncurredDTO) {
        var costsIncurred = CostsIncurred.builder()
                .quantity(costsIncurredDTO.getQuantity())
                .price(costsIncurredDTO.getPrice())
                .date(costsIncurredDTO.getDate())
                .note(costsIncurredDTO.getNote())
                .user(userRepository.findById(costsIncurredDTO.getUserId()).orElse(null))
                .build();

        costsIncurredService.save(costsIncurred);
    }
}
