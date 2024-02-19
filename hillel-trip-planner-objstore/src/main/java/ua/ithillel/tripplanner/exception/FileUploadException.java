package ua.ithillel.tripplanner.exception;

import ua.ithillel.tripplanner.model.dto.FileUploadDTO;

public class FileUploadException extends Exception {
    public FileUploadException(String message, FileUploadDTO fileUploadDTO) {
        super(String.format("Unable to upload the file %s: %s", fileUploadDTO.getFileName(), message));
    }
}
