package com.example.nursinghome.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nursinghome.entitydto.ServiceInfoRequest;
import com.example.nursinghome.entitydto.ServiceInfoResponse;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.ServiceInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceInfoService {

    private final ServiceInfoRepository serviceInfoRepository;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    public void AddServiceInfo(HttpServletRequest request, ServiceInfoRequest serviceInfoRequest) {
        try {
            // Tạo đối tượng Cloudinary
            Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
            Map<?, ?> result = cloudinary.uploader().upload(serviceInfoRequest.getMultipartFile().getBytes(), ObjectUtils.emptyMap());
            // Lấy URL của ảnh tải lên từ kết quả
            String imageUrl = (String) result.get("url");
            var serviceInfo = ServiceInfo.builder()
                            .name(serviceInfoRequest.getName())
                            .type(serviceInfoRequest.getType())
                            .priceDay(serviceInfoRequest.getPriceDay())
                            .priceWeek(serviceInfoRequest.getPriceWeek())
                            .priceMonth(serviceInfoRequest.getPriceMonth())
                            .priceYear(serviceInfoRequest.getPriceYear())
                            .ticketPrices(serviceInfoRequest.getTicketPrices())
                            .descriptionService(serviceInfoRequest.getDescriptionService())
                            .imageUrl(imageUrl)
                            .build();

            serviceInfoRepository.save(serviceInfo);
        } catch (IOException e) {
            // Xử lý lỗi tải lên ảnh
            throw  new RoleException("Error");
        }
    }

    public List<ServiceInfoResponse> getAllServiceInfo() {
        List<ServiceInfo> serviceInfos = serviceInfoRepository.findAll();
        return serviceInfos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ServiceInfoResponse mapToDTO(ServiceInfo serviceInfo) {
        ServiceInfoResponse dto = new ServiceInfoResponse();
        dto.setName(serviceInfo.getName());
        dto.setType(serviceInfo.getType());
        dto.setPriceDay(serviceInfo.getPriceDay());
        dto.setPriceWeek(serviceInfo.getPriceWeek());
        dto.setPriceMonth(serviceInfo.getPriceMonth());
        dto.setPriceYear(serviceInfo.getPriceYear());
        dto.setTicketPrices(serviceInfo.getTicketPrices());
        dto.setDescriptionService(serviceInfo.getDescriptionService());
        dto.setImageUrl(serviceInfo.getImageUrl());
        return dto;
    }
}
