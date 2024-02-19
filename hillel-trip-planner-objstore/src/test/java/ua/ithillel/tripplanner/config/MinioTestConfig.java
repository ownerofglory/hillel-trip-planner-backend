package ua.ithillel.tripplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:minio-test-config.properties")
public class MinioTestConfig {
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.bucketName}")
    private String minioBucketName;

    @Bean
    public MinioBucketInfo minioBucketInfo() {
        return new MinioBucketInfo(minioEndpoint, minioBucketName);
    }

}
