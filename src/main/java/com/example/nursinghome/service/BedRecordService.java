package com.example.nursinghome.service;

import com.example.nursinghome.entity.BedRecord;
import com.example.nursinghome.entitydto.BedRecordDTO;
import com.example.nursinghome.repository.BedRecordRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BedRecordService {
    private final BedRecordRepository bedRecordRepository;

    public void addBedRecord(HttpServletRequest httpServletRequest, BedRecordDTO bedRecordDTO) {
        BedRecord bedRecord = BedRecord.builder()
                .bedIdFk(bedRecordDTO.getBedIdFk())
                .userIdFk(bedRecordDTO.getUserIdFk())
                .productionDate(bedRecordDTO.getProductionDate())
                .expirationDate(bedRecordDTO.getExpirationDate())
                .build();
        bedRecordRepository.save(bedRecord);
    }
}
