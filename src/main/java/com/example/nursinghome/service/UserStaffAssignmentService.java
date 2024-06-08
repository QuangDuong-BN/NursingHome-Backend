package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.ServiceRecord;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.entity.UserStaffAssignment;
import com.example.nursinghome.entitydto.UserStaffAssignmentDTO;
import com.example.nursinghome.projectioninterface.UserProjection;
import com.example.nursinghome.repository.ServiceRecordRepository;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.repository.UserStaffAssignmentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserStaffAssignmentService {
    private final UserRepository userRepository;
    private final ServiceRecordRepository serviceRecordRepository;
    private final JwtService jwtService;
    private final UserStaffAssignmentRepository userStaffAssignmentRepository;

    public List<UserStaffAssignment> getAllAssignments() {
        return userStaffAssignmentRepository.findAll();
    }

    public UserStaffAssignment getAssignmentById(Long id) {
        return userStaffAssignmentRepository.findById(id).orElse(null);
    }

    public UserStaffAssignment saveAssignment(UserStaffAssignmentDTO assignment) {
        User user = userRepository.findById(assignment.getUserId()).orElse(null);
        User staff = userRepository.findById(assignment.getStaffId()).orElse(null);
        ServiceRecord serviceRecord = serviceRecordRepository.findById(assignment.getServiceRecordId()).orElse(null);
        UserStaffAssignment userStaffAssignment = UserStaffAssignment.builder()
                .user(user)
                .staff(staff)
                .serviceRecord(serviceRecord)
                .build();
        return userStaffAssignmentRepository.save(userStaffAssignment);
    }

    public void deleteAssignment(Long id) {
        userStaffAssignmentRepository.deleteById(id);
    }

    public List<UserProjection> getListUserByDocter(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        User familyMember = userRepository.getUerByUserName(username);
        return userStaffAssignmentRepository.getAllUserByDocter(familyMember);
    }
}