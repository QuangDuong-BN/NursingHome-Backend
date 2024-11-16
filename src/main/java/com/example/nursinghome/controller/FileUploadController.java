package com.example.nursinghome.controller;

import com.example.nursinghome.uploadfileconfig.FileUpLoadResponse;
import com.example.nursinghome.uploadfileconfig.FileUploadUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/uploadfile")
public class FileUploadController {
    @PostMapping()
    public String upLoadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size=multipartFile.getSize();
        FileUploadUtil.saveFile(fileName,multipartFile);
        FileUpLoadResponse fileUpLoadResponese= new FileUpLoadResponse(fileName,"/dowloadFile",size);
        return "success";
    }
}