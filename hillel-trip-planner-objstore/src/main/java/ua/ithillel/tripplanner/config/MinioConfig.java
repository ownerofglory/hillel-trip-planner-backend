package ua.ithillel.tripplanner.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:minio-config.properties")
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.accessKey}")
    private String minioAccessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName}")
    private String minioBucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, secretKey)
                .build();
    }

    @Bean
    public MinioBucketInfo minioBucketInfo() {
        return new MinioBucketInfo(minioEndpoint, minioBucketName);
    }
}
