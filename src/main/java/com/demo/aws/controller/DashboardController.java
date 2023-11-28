package com.demo.aws.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {
    private final String bucketName;
    private final AmazonS3Client amazonS3Client;
    private String bucketLocation;

    @Autowired
    public DashboardController(@Value("${custom.bucket-name") String bucketName,
                               AmazonS3Client amazonS3Client){
        this.bucketName = bucketName;
        this.amazonS3Client = amazonS3Client;
    }

    @PostConstruct
    public void postConstruct(){
        this.bucketLocation = String.format("https://%s.s3.%s.amazonaws.com", bucketName,
                this.amazonS3Client.getBucketLocation(bucketName));
    }
}
