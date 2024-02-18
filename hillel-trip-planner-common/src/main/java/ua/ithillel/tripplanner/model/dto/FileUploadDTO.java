package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileUploadDTO {
    private String fileName;
    private String contentType;
    private Long size;
    private InputStream in;
}
