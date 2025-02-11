package com.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.util.pathConstants;

@Configuration
public class AwsS3Config {

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(pathConstants.ACCESS_KEY, pathConstants.SECRET_KEY);
        return AmazonS3ClientBuilder.standard()
                .withRegion(pathConstants.AWS_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
