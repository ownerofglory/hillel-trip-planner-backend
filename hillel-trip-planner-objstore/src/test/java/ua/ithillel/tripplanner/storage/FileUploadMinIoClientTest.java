package ua.ithillel.tripplanner.storage;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.ithillel.tripplanner.config.MinioBucketInfo;
import ua.ithillel.tripplanner.config.MinioTestConfig;
import ua.ithillel.tripplanner.exception.FileUploadException;
import ua.ithillel.tripplanner.model.dto.FileUploadDTO;
import ua.ithillel.tripplanner.model.dto.FileUploadResultDTO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MinioTestConfig.class)
public class FileUploadMinIoClientTest {
    @Autowired
    private MinioBucketInfo minioBucketInfo;

    @Mock
    private MinioClient minioClientMock;
    private FileUploadClient fileUploadClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        fileUploadClient = new FileUploadMinIoClient(minioClientMock, minioBucketInfo);
    }
    
    @Test
    public void uploadFileTest_success() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, FileUploadException {
        byte[] testDate = "test".getBytes();
        final FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFileName("test");
        fileUploadDTO.setSize((long) testDate.length);
        fileUploadDTO.setIn(new ByteArrayInputStream(testDate));
        fileUploadDTO.setContentType("test");

        final ObjectWriteResponse mockResult = new ObjectWriteResponse(null, minioBucketInfo.getBucketName(), "", "", "", "");
        when(minioClientMock.putObject(any())).thenReturn(mockResult);


        final FileUploadResultDTO result = fileUploadClient.uploadFile(fileUploadDTO);

        assertNotNull(result);
    }

    @Test
    public void uploadFileTest_throws() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, FileUploadException {
        byte[] testDate = "test".getBytes();
        final FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFileName("test");
        fileUploadDTO.setSize((long) testDate.length);
        fileUploadDTO.setIn(new ByteArrayInputStream(testDate));
        fileUploadDTO.setContentType("test");

        when(minioClientMock.putObject(any())).thenThrow(new ServerException("", -1, ""));

        assertThrows(FileUploadException.class, () -> fileUploadClient.uploadFile(fileUploadDTO));
    }
}
