package ua.ithillel.tripplanner.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.exception.InvalidImageException;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;
import ua.ithillel.tripplanner.service.ImageUploadService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageUploadControllerTest extends ControllerTestParent {
    @Mock
    private ImageUploadService imageUploadServiceMock;
    private ImageUploadController imageUploadController;
    private MockMultipartFile imageFile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        byte[] validFileContent = "Mock image content".getBytes();
        imageFile = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", validFileContent);

        imageUploadController = new ImageUploadController(imageUploadServiceMock);

        this.mockMvc = MockMvcBuilders.standaloneSetup(imageUploadController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    void uploadImage_ValidImage_Success() throws Exception {
        FileUploadResultDTO mockResult = new FileUploadResultDTO();
        mockResult.setUploaded(true);
        mockResult.setUrl("testUrl");

        when(imageUploadServiceMock.uploadImage(any())).thenReturn(mockResult);


        mockMvc.perform(multipart("/imageUpload")
                        .file("file", imageFile.getBytes())
                        .param("filename", "test.jpg")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uploaded").value(true))
                .andExpect(jsonPath("$.url").value("testUrl"));
    }

    @Test
    void uploadImage_ThrowInvalidImage_Exception() throws Exception {
        when(imageUploadServiceMock.uploadImage(any())).thenThrow(InvalidImageException.class);


        mockMvc.perform(multipart("/imageUpload")
                        .file("file", imageFile.getBytes())
                        .param("filename", "test.jpg")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void uploadImage_ThrowFileUpload_Exception() throws Exception {
        when(imageUploadServiceMock.uploadImage(any())).thenThrow(FileUploadException.class);


        mockMvc.perform(multipart("/imageUpload")
                        .file("file", imageFile.getBytes())
                        .param("filename", "test.jpg")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}