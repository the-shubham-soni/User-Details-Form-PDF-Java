package com.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.util.pathConstants;
@Configuration
public class AwsSqsConfig {

    @Bean
    public AmazonSQS amazonSQS() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(pathConstants.ACCESS_KEY, pathConstants.SECRET_KEY);
        return AmazonSQSClientBuilder.standard()
                .withRegion(pathConstants.AWS_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}