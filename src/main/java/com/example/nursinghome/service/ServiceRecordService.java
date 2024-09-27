package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.ServiceInfo;
import com.example.nursinghome.model.ServiceRecord;
import com.example.nursinghome.model.User;
import com.example.nursinghome.model.UserStaffAssignment;
import com.example.nursinghome.entitydto.ServiceRecordDTO;
import com.example.nursinghome.enumcustom.PaymentStatus;
import com.example.nursinghome.enumcustom.RecordStatus;
import com.example.nursinghome.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceRecordService {
    private final ServiceRecordRepository serviceRecordRepository;
    private final JwtService jwtService;
    private final BedRepository bedRepository;
    private final ServiceInfoRepository serviceInfoRepository;
    private final UserRepository userRepository;
    private final UserStaffAssignmentRepository userStaffAssignmentRepository;

    public void addServiceRecord(HttpServletRequest httpServletRequest, ServiceRecordDTO serviceRecordDTO) {
        // check role
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        User userFamily = userRepository.getUerByUserName(username);

        // thong tin nguoi du dung
        User user = userRepository.getUserById(serviceRecordDTO.getUserIdFk());

        // service info
        ServiceInfo serviceInfo = serviceInfoRepository.getServiceInfoById(serviceRecordDTO.getServiceInfoIdFk());
        Double priceDay = serviceInfo.getPriceDay();
        Double priceWeek = serviceInfo.getPriceWeek();
        Double priceMonth = serviceInfo.getPriceMonth();
        Double priceYear = serviceInfo.getPriceYear();

        // time su dung dich vu
        Timestamp productionDate = serviceRecordDTO.getProductionDate();
        Timestamp expirationDate = serviceRecordDTO.getExpirationDate();
        LocalDate date = expirationDate.toLocalDateTime().toLocalDate();

        // Đặt giờ thành 5 PM
        LocalDateTime dateTime = date.atTime(17, 0); // 17 giờ tương ứng với 5 PM
        // Chuyển LocalDateTime về Timestamp
        expirationDate = Timestamp.valueOf(dateTime);

        // Chuyển Timestamp thành LocalDate
        LocalDate productionLocalDate = productionDate.toLocalDateTime().toLocalDate();
        LocalDate expirationLocalDate = expirationDate.toLocalDateTime().toLocalDate();
        // tinh tiền dịch vụ
        long numberOfDays = Math.abs(productionLocalDate.until(expirationLocalDate).getDays()) + 1;

        Double price = 0.0;
        if (numberOfDays < 7) {
            price = priceDay * numberOfDays;
        } else if (numberOfDays < 30) {
            price = priceWeek * (numberOfDays / 7);
        } else if (numberOfDays < 365) {
            price = priceMonth * (numberOfDays / 30);
        } else {
            price = priceYear * (numberOfDays / 365);
        }

        // dang ki giuong benh
//        Bed bed = bedRepository.getBedByID(serviceRecordDTO.getBedIdFk());


        var serviceRecord = ServiceRecord.builder()
                .userIdFk(user)
                .familyMemberIdFk(userFamily)
                .serviceInfoIdFk(serviceInfo)
                .price(price)
                .productionDate(productionDate)
                .expirationDate(expirationDate)
                .paymentStatus(PaymentStatus.UNPAID)
                .bookingTime(new Timestamp(System.currentTimeMillis()))
                .roomType(serviceRecordDTO.getRoomType())
                .paymentTime(null)
                .recordStatus(RecordStatus.ACTIVE)
                .build();

        ServiceRecord savedServiceRecord = serviceRecordRepository.save(serviceRecord);

        User userDocter = userRepository.getUserById(6L);
        User userNurse = userRepository.getUserById(7L);
        UserStaffAssignment userStaffAssignment = UserStaffAssignment.builder()
                .user(user)
                .staff(userDocter)
                .serviceRecord(savedServiceRecord)
                .build();
        userStaffAssignmentRepository.save(userStaffAssignment);

        userStaffAssignment = UserStaffAssignment.builder()
                .user(user)
                .staff(userNurse)
                .serviceRecord(savedServiceRecord)
                .build();
        userStaffAssignmentRepository.save(userStaffAssignment);
    }

    public List<Object[]> getListServiceRecord(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        User user = userRepository.getUerByUserName(username);
        return serviceRecordRepository.getAllByIdFamilyMemberIdFk(user.getId());
    }

    public Object getServiceRecordById(HttpServletRequest httpServletRequest, Long id) {
        return serviceRecordRepository.getServiceById(id);
    }

    public String deleteEntityById(HttpServletRequest httpServletRequest, Long id) {
        // Kiểm tra xem đối tượng cần xóa có tồn tại hay không
        Optional<ServiceRecord> entity = serviceRecordRepository.findById(id);
        if (entity.isPresent()) {
            // Thực hiện xóa đối tượng
            serviceRecordRepository.deleteById(id);

            // Kiểm tra xem đối tượng còn tồn tại sau khi xóa hay không
            entity = serviceRecordRepository.findById(id);
            if (!entity.isPresent()) {
                // Nếu đối tượng không còn tồn tại, trả về true
                return "success";
            }
        }
        // Nếu đối tượng không tồn tại hoặc xóa không thành công, trả về false
        return "fail";
    }

    public String updateServiceRecordById(HttpServletRequest httpServletRequest, Long id) {
        serviceRecordRepository.updateServiceRecordById(id);
        return "success";
    }
}
