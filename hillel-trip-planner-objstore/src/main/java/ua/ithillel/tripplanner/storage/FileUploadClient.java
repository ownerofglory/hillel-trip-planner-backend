package ua.ithillel.tripplanner.storage;

import ua.ithillel.tripplanner.model.dto.FileUploadDTO;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;

public interface FileUploadClient {
    FileUploadResultDTO uploadFile(FileUploadDTO file);
}
