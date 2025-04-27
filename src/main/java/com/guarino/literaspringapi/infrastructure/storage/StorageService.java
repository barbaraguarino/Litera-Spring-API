package com.guarino.literaspringapi.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class StorageService {

    private final S3Client s3Client;
    private final String awsBucket;
    private final String awsRegion;

    public StorageService(S3Client s3Client, @Value("${aws.s3.bucket-name}") String awsBucket,
                          @Value("${aws.region}") String awsRegion) {
        this.s3Client = s3Client;
        this.awsBucket = awsBucket;
        this.awsRegion = awsRegion;
    }

    public String uploadFile(MultipartFile file, String contentType) throws IOException {
        String fileName = UUID.randomUUID() + "." + file.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(awsBucket)
                .key(fileName)
                .contentType(contentType)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        return String.format("https://%s.s3.%s.amazonaws.com/%s", awsBucket, awsRegion, fileName);
    }
}
