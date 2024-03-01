package ua.ithillel.tripplanner.service;

import org.springframework.web.multipart.MultipartFile;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.exception.InvalidImageException;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;

public interface ImageUploadService {
    FileUploadResultDTO uploadImage(MultipartFile file) throws InvalidImageException, FileUploadException;
}
