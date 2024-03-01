package ua.ithillel.tripplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.exception.InvalidImageException;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;
import ua.ithillel.tripplanner.service.ImageUploadService;

@RestController
@RequestMapping("/imageUpload")
@RequiredArgsConstructor
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<String>
    uploadImage(@RequestParam("file") MultipartFile file) throws InvalidImageException, FileUploadException {
        FileUploadResultDTO fileUploadResultDTO = imageUploadService.uploadImage(file);

        return ResponseEntity.ok("Image uploaded successfully. Url: " + fileUploadResultDTO.getUrl());
    }
}
