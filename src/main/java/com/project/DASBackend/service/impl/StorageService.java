package com.project.DASBackend.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file, Integer assessmentPaperId) throws AmazonServiceException, SdkClientException, IOException {
        File fileObj = convertMultiPartFileToFile(file);

        // Tạo tên file upload với phần mở rộng .png
        String originalFileName = file.getOriginalFilename();
        String fileName = "AssessmentPaper_" + assessmentPaperId + "_" + originalFileName;
        // Kiểm tra nếu tên file không có phần mở rộng, thêm .png
        if (!originalFileName.toLowerCase().endsWith(".png")) {
            fileName += ".png";
        }
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        } catch (AmazonServiceException e) {
            log.error("Amazon service error: {}", e.getMessage());
            throw e;
        } catch (SdkClientException e) {
            log.error("SDK client error: {}", e.getMessage());
            throw e;
        } finally {
            if (fileObj.exists() && !fileObj.delete()) {
                log.warn("Failed to delete temporary file: {}", fileObj.getAbsolutePath());
            }
        }

        return fileName;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public Resource dowloadFile(String fileName) throws IOException {
//        S3Object s3Object = s3Client.getObject(bucketName, fileName);
//        var bytes = s3Object.getObjectContent().readAllBytes();
//        Resource resource = new ByteArrayResource(bytes);
//        return resource;
//    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
            throw new RuntimeException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

}
