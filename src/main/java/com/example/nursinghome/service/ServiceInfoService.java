package com.example.nursinghome.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.ServiceInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.nursinghome.dto.ServiceInfoDTO;

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

    public void AddServiceInfo(HttpServletRequest request, String name, String descriptionService , MultipartFile file) {
        try {
            Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            // Lấy URL của ảnh tải lên từ kết quả
            String imageUrl = (String) result.get("url");
            var serviceInfo = ServiceInfo.builder()
                    .name(name)
                    .descriptionService(descriptionService)
                    .imageUrl(imageUrl)
                    .build();

            serviceInfoRepository.save(serviceInfo);
        } catch (IOException e) {
            // Xử lý lỗi tải lên ảnh
            throw  new RoleException("Error");
        }
    }

    public List<ServiceInfoDTO> getAllServiceInfo() {
        List<ServiceInfo> serviceInfos = serviceInfoRepository.findAll();
        return serviceInfos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ServiceInfoDTO mapToDTO(ServiceInfo serviceInfo) {
        ServiceInfoDTO dto = new ServiceInfoDTO();
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
