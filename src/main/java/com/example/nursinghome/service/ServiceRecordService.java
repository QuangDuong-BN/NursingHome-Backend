package com.example.nursinghome.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.nursinghome.repository.ServiceRecordRepository;
@RequiredArgsConstructor
@Service
public class ServiceRecordService {
    private final ServiceRecordRepository serviceRecordRepository;

}
