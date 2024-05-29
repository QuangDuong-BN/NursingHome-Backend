package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.*;
import com.example.nursinghome.entitydto.BedRecordDTO;
import com.example.nursinghome.entitydto.ServiceRecordDTO;
import com.example.nursinghome.enumcustom.PaymentStatus;
import com.example.nursinghome.enumcustom.RecordStatus;
import com.example.nursinghome.repository   .BedRepository;
import com.example.nursinghome.repository.ServiceInfoRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.nursinghome.repository.ServiceRecordRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
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

        // time su dung dich vu
        Timestamp productionDate = serviceRecordDTO.getProductionDate();
        Timestamp expirationDate = serviceRecordDTO.getExpirationDate();


        // Chuyển Timestamp thành LocalDate
        LocalDate productionLocalDate = productionDate.toLocalDateTime().toLocalDate();
        LocalDate expirationLocalDate = expirationDate.toLocalDateTime().toLocalDate();
        // tinh tiêền dịch vụ
        long numberOfDays = Math.abs(productionLocalDate.until(expirationLocalDate).getDays());

        // dang ki giuong benh
        Bed bed = bedRepository.getBedByID(serviceRecordDTO.getBedIdFk());

//        BedRecordDTO bedRecordDTO = BedRecordDTO.builder()
//                .bedIdFk(bed)
//                .userIdFk(user)
//                .productionDate(productionDate)
//                .expirationDate(expirationDate)
//                .build();
//        BedRecord BedRecord = bedRecordService.addBedRecord(httpServletRequest, bedRecordDTO);

        var serviceRecord = ServiceRecord.builder()
                .userIdFk(user)
                .familyMemberIdFk(userFamily)
                .serviceInfoIdFk(serviceInfo)
                .bedIdFk(bed)
                .price(120000.0*(numberOfDays+1))
                .productionDate(productionDate)
                .expirationDate(expirationDate)
                .paymentStatus(PaymentStatus.PAID)
                .bookingTime(new Timestamp(System.currentTimeMillis()))
                .roomType(serviceRecordDTO.getRoomType())
                .paymentTime(null)
                .recordStatus(RecordStatus.ACTIVE)
                .build();

        serviceRecordRepository.save(serviceRecord);
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

    public String deleteEntityById(HttpServletRequest httpServletRequest,Long id) {
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
