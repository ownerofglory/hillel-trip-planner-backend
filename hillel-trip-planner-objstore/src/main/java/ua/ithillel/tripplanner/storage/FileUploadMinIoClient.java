package ua.ithillel.tripplanner.storage;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.config.MinioBucketInfo;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.model.dto.FileUploadDTO;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class FileUploadMinIoClient implements FileUploadClient {
    private final MinioClient minioClient;
    private final MinioBucketInfo minioBucketInfo;

    @Override
    public FileUploadResultDTO uploadFile(FileUploadDTO fileUploadDTO) throws FileUploadException {
        try {
            PutObjectArgs putObjectArgs = createPutObjectArgs(fileUploadDTO);
            ObjectWriteResponse writeResponse = minioClient.putObject(putObjectArgs);

            return createFileUploadResultDTO(fileUploadDTO);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new FileUploadException(e.getMessage(), fileUploadDTO);
        }
    }

    private PutObjectArgs createPutObjectArgs(FileUploadDTO fileUploadDTO) {
        return PutObjectArgs.builder()
                .bucket(minioBucketInfo.getBucketName())
                .object(fileUploadDTO.getFileName())
                .stream(fileUploadDTO.getIn(), fileUploadDTO.getSize(), -1)
                .contentType(fileUploadDTO.getContentType())
                .build();
    }

    private FileUploadResultDTO createFileUploadResultDTO(FileUploadDTO fileUploadDTO) {
        FileUploadResultDTO fileUploadResultDTO = new FileUploadResultDTO();

        String fileUrl = String.format("%s/%s/%s", minioBucketInfo.getUrl(),
                minioBucketInfo.getBucketName(), fileUploadDTO.getFileName().replace(" ", "%20"));
        fileUploadResultDTO.setUrl(fileUrl);

        fileUploadResultDTO.setUploaded(true);

        return fileUploadResultDTO;
    }
}
