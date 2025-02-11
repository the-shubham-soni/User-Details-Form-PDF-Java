package com.example.util;

public class pathConstants {
	
	// AWS credentials
	public static final String AWS_REGION = "ap-south-1";
	public static final String ACCESS_KEY = "AKIAWFIPS67UXXH5N4BS";
	public static final String SECRET_KEY = "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";
	public static final String QUEUE_URL = "https://sqs.ap-south-1.amazonaws.com/423623849961/UserDetailsQueue";
	public static final String BUCKET_NAME = "user-details-pdfs";
	public static final String BUCKET_FILE_NAME = "UserDetails.pdf";
	
	// CORS credentials
	public static final String CORS_PATH = "http://localhost:4200";
	public static final String CORS_MAPPING = "/api/**";
	
	// MESSAGES
	public static final String S3_UPLOAD_SUCCESS = "Successfully uploaded your details to S3.";
	public static final String S3_UPLOAD_FAIL = "Failed to upload your details.";
	
	// GET/POST MAPPING PATH
	public static final String GET_ALL_USERS_PATH = "/getAll";
	public static final String REQUEST_MAPPING_PATH = "/api/users";
	public static final String POST_MAPPING_PATH = "/send";
}
