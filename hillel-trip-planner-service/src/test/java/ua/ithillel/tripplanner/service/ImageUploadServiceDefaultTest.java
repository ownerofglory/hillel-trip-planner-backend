package ua.ithillel.tripplanner.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.exception.InvalidImageException;
import ua.ithillel.tripplanner.model.dto.FileUploadDTO;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;
import ua.ithillel.tripplanner.storage.FileUploadClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ImageUploadServiceDefaultTest extends ServiceTestParent {
    @Mock
    private FileUploadClient fileUploadClient;
    private ImageUploadServiceDefault imageUploadService;
    private MockMultipartFile validImageFile;
    private MockMultipartFile invalidImageFile;


    @BeforeEach
    void setUp()  {
        MockitoAnnotations.openMocks(this);

        imageUploadService = new ImageUploadServiceDefault(fileUploadClient);

        byte[] validFileContent = "Mock image content".getBytes();
        validImageFile = new MockMultipartFile("valid.jpg", "test.jpg", "image/jpeg", validFileContent);

        byte[] invalidFileContent = "Mock text file content".getBytes();
        invalidImageFile = new MockMultipartFile("test.txt", "test.txt", "text/plain", invalidFileContent);
    }

    @Test
    void uploadImage_ValidImage_SuccessfulUpload() throws FileUploadException, InvalidImageException {
        FileUploadResultDTO fileUploadResultDTO = new FileUploadResultDTO();
        fileUploadResultDTO.setUrl("test_url");
        fileUploadResultDTO.setUploaded(true);

        when(fileUploadClient.uploadFile(any())).thenReturn(fileUploadResultDTO);


        FileUploadResultDTO result = imageUploadService.uploadImage(validImageFile);

        assertNotNull(result);
        assertNotNull(result.getUrl());
        assertTrue(result.isUploaded());
    }

    @Test
    void uploadNotImage_ThrowsInvalidImageException() {
        assertThrows(InvalidImageException.class, () -> imageUploadService.uploadImage(invalidImageFile));
    }

    @Test
    void uploadImage_ThrowsFileUploadException() throws FileUploadException {
        when(fileUploadClient.uploadFile(any())).thenThrow(new FileUploadException("file not uploads", new FileUploadDTO()));

        assertThrows(FileUploadException.class, () -> imageUploadService.uploadImage(validImageFile));
    }
}