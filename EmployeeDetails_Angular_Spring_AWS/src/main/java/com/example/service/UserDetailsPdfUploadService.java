package com.example.service;

import com.example.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.example.util.pathConstants;

@Service
public class UserDetailsPdfUploadService {

    private final AmazonS3 s3;

    @Autowired
    public UserDetailsPdfUploadService(AmazonS3 s3) {
        this.s3 = s3;
    }

    public boolean createPdfAndUpload(User user) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add user details to the PDF
            document.add(new Paragraph("User Details"));
            document.add(new Paragraph("Username: " + user.getUsername()));
            document.add(new Paragraph("Age: " + user.getAge()));
            document.add(new Paragraph("Address: " + user.getAddress()));
            document.add(new Paragraph("Contact: " + user.getContact()));
            document.add(new Paragraph("Role: " + user.getRole()));

            document.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());

            // Use the username as the filename for the PDF
            String pdfFileName = user.getUsername() + ".pdf";

            PutObjectRequest request = new PutObjectRequest(pathConstants.BUCKET_NAME, pdfFileName, inputStream, null);
            PutObjectResult result = s3.putObject(request);
            return result != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
