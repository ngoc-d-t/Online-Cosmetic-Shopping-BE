package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.service.UploadImageService;
import com.ngocdt.tttn.utils.DriveAPIUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;

@Service
public class UploadImageServiceImpl implements UploadImageService {
    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public String upload(String file) {
        String fileName = UUID.randomUUID().toString();
        try {
            FileCopyUtils.copy(Base64Utils.decodeFromString(file.split(",")[1]),
                    new File(this.fileUpload + fileName));
            return DriveAPIUtils.upload(new File(fileUpload + fileName));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadRequestException("image-wrong");
        }
    }
}
