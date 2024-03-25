package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.exception.InvalidImageException;
import ua.ithillel.tripplanner.model.dto.FileUploadDTO;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;
import ua.ithillel.tripplanner.storage.FileUploadClient;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ImageUploadServiceDefault implements ImageUploadService {
    private final FileUploadClient fileUploadClient;

    @Override
    public FileUploadResultDTO uploadImage(MultipartFile file) throws InvalidImageException, FileUploadException {
        if (!isImage(file)) {
            throw new InvalidImageException("Uploaded file is not an image.");
        }

        FileUploadDTO fileUploadDTO = convertToFileUploadDTO(file);
        FileUploadResultDTO fileUploadResultDTO = fileUploadClient.uploadFile(fileUploadDTO);

        return fileUploadResultDTO;
    }

    private boolean isImage(MultipartFile file)  {
        String contentType = file.getContentType();

        return contentType != null && contentType.startsWith("image");
    }

    private FileUploadDTO convertToFileUploadDTO(MultipartFile file) {
        FileUploadDTO fileUploadDTO = null;
        try {
            fileUploadDTO = new FileUploadDTO();
            fileUploadDTO.setFileName(file.getOriginalFilename());
            fileUploadDTO.setSize(file.getSize());
            fileUploadDTO.setContentType(file.getContentType());
            fileUploadDTO.setIn(file.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUploadDTO;
    }
}
