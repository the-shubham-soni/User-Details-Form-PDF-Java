package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserDetailsPdfUploadService;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.util.pathConstants;
@RestController
@RequestMapping(pathConstants.REQUEST_MAPPING_PATH)
public class UserController {

    private final AmazonSQS sqs;
    private final UserDetailsPdfUploadService pdfService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(AmazonSQS sqs, UserDetailsPdfUploadService pdfService, UserRepository userRepository) {
        this.sqs = sqs;
        this.pdfService = pdfService;
        this.userRepository = userRepository;
    }

    @PostMapping(pathConstants.POST_MAPPING_PATH)
    public ResponseEntity<Map<String, String>> sendUserDetails(@RequestBody User user) {
       
    	// Save user to MySQL
        userRepository.save(user);

        String messageBody = "User Details: " + user;

        // Send user details to SQS
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(pathConstants.QUEUE_URL)
                .withMessageBody(messageBody);

        sqs.sendMessage(sendMsgRequest);

        // Generate PDF and upload to S3
        boolean uploadSuccess = pdfService.createPdfAndUpload(user);

        Map<String, String> response = new HashMap<>();
        if (uploadSuccess) {
            response.put("message", pathConstants.S3_UPLOAD_SUCCESS);
        } else {
            response.put("message", pathConstants.S3_UPLOAD_FAIL);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(pathConstants.GET_ALL_USERS_PATH)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
