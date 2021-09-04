package com.ngocdt.tttn.controller;

import com.ngocdt.tttn.dto.Image;
import com.ngocdt.tttn.service.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/admin/images")
public class UploadImageController {
    private final UploadImageService uploadImageService;

    @PostMapping()
    public ResponseEntity<String> uploadImage(@Valid @RequestBody Image image){
        return ResponseEntity.ok().body(uploadImageService.upload(image.getBase64String()));
    }
}
