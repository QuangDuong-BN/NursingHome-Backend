package com.example.nursinghome.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.exception.ErrorResponse;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.ServiceInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
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

    public void AddService(HttpServletRequest request,String name,String descriptionService , MultipartFile file) {
        try {
            Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            // Lấy URL của ảnh tải lên từ kết quả
            String imageUrl = (String) result.get("url");
            var serviceInfo = ServiceInfo.builder()
                    .name(name)
                    .description_service(descriptionService)
                    .image_url(imageUrl)
                    .build();

            serviceInfoRepository.save(serviceInfo);
        } catch (IOException e) {
            // Xử lý lỗi tải lên ảnh
            throw  new RoleException("Error");
        }
    }
}
