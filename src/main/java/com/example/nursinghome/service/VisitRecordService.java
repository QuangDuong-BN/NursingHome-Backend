package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.entity.VisitRecord;
import com.example.nursinghome.entitydto.VisitRecordDTO;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.repository.VisitRecordReponsitory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VisitRecordService {
    private final VisitRecordReponsitory visitReponsitory;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public List<Object[]> getVisitHistoryByDate(HttpServletRequest httpServletRequest,Date visitDate) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        User user = userRepository.getUerByUserName(username);

        return visitReponsitory.findVisitRecordByVisitorAndVisitDate(user,visitDate);
    }

    public List<Object[]> getVisitforUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        User visitor = userRepository.getUerByUserName(username);

        return visitReponsitory.findVisitRecordByVisitor(visitor);
    }

    public VisitRecord addVisitHistory(HttpServletRequest httpServletRequest, VisitRecordDTO visitRecordDTO) {
        VisitRecord visitRecord = new VisitRecord();
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token

        // theem nguoi toi tham
        User visitor = userRepository.getUerByUserName(username);
        visitRecord.setVisitor(visitor);
        // nguoi duoc tham
        Optional<User> visited = userRepository.findById(visitRecordDTO.getVisitedId());
        visitRecord.setVisited(visited.get());
        //
        visitRecord.setTimeOfDay(visitRecordDTO.getTimeOfDay());
        //
        visitRecord.setVisitDate(visitRecordDTO.getVisitDate());

        return visitReponsitory.save(visitRecord);
    }
}
