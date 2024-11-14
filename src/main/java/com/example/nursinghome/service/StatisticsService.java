package com.example.nursinghome.service;

import com.example.nursinghome.constants.enums.RoleUser;
import com.example.nursinghome.repository.ServiceRecordRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private final ServiceRecordRepository serviceRecordRepository;
    public Integer countStaff(HttpServletRequest request) {
        Integer countDocter= userRepository.countAllByRole(RoleUser.DOCTOR);
        Integer countNURSE= userRepository.countAllByRole(RoleUser.NURSE);
        return countDocter+countNURSE;
    }

    public Integer countUser(HttpServletRequest request) {
        return userRepository.countAllByRole(RoleUser.SERVICE_USER);
    }

    public Double countRevenue(HttpServletRequest request) {
        return serviceRecordRepository.countRevenue();
    }


}
