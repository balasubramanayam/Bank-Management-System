//package com.bms.util;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailUtil {
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendOtpEmail(String email, String accountNumber,  String firstName,
//                              String lastName,String randomPassword,  String mobile, String gender,LocalDate accountCreationDate) throws MessagingException {
//        String htmlContent = buildEmailContent(email, accountNumber,firstName, lastName, randomPassword, mobile, gender, accountCreationDate);
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        mimeMessageHelper.setTo(email);
//        mimeMessageHelper.setSubject("Account Details");
//        mimeMessageHelper.setText(htmlContent, true);
//
//        javaMailSender.send(mimeMessage);
//    }
//
//    private String buildEmailContent(String email, String accountNumber, String firstName,
//            String lastName, String randomPassword, String mobile, String gender, LocalDate accountCreationDate) {
//        return "<html><head>" +
//                "<style>" +
//                "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
//                ".email-container { background-color: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }" +
//                "h1 { color: #333333; }" +
//                "p { color: #555555; line-height: 1.6; }" +
//                ".footer { margin-top: 20px; font-size: 12px; color: #999999; }" +
//                ".highlight { color: #007BFF; font-weight: bold; }" +
//                "</style>" +
//                "</head><body>" +
//                "<div class='email-container'>" +
//                "<h1>Account Details</h1>" +
//                "<p>Hello <span class='highlight'>" + firstName + " " + lastName + "</span>,</p>" +
//                "<p>Your account has been created successfully!</p>" +
//                "<p><strong>Account Number:</strong> " + accountNumber + "</p>" +
//                "<p><strong>Temporary Password:</strong> " + randomPassword + "</p>" +
//                "<p><strong>Mobile:</strong> " + mobile + "</p>" +
//                "<p><strong>Gender:</strong> " + gender + "</p>" +
//                "<p><strong>Account Creation Date:</strong> " + accountCreationDate + "</p>" +
//                "</div></body></html>";
//    }
//
//}


package com.bms.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String accountNumber, String firstName,
                              String lastName, String randomPassword, String mobile,
                              String gender) throws MessagingException {
        String htmlContent = buildEmailContent(email, accountNumber, firstName, lastName, randomPassword, mobile, gender);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Account Details");
        mimeMessageHelper.setText(htmlContent, true);

        javaMailSender.send(mimeMessage);
    }

    private String buildEmailContent(String email, String accountNumber, String firstName,
                                      String lastName, String randomPassword, String mobile,
                                      String gender) {
       
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedCurrentDate = currentDate.format(formatter);

        return "<html><head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                ".email-container { background-color: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333333; }" +
                "p { color: #555555; line-height: 1.6; }" +
                ".footer { margin-top: 20px; font-size: 12px; color: #999999; }" +
                ".highlight { color: #007BFF; font-weight: bold; }" +
                "</style>" +
                "</head><body>" +
                "<div class='email-container'>" +
                "<h1>Account Details</h1>" +
                "<p>Hello <span class='highlight'>" + firstName + " " + lastName + "</span>,</p>" +
                "<p>Your account has been created successfully!</p>" +
                "<p><strong>Account Number:</strong> " + accountNumber + "</p>" +
                "<p><strong>Temporary Password:</strong> " + randomPassword + "</p>" +
                "<p><strong>Mobile:</strong> " + mobile + "</p>" +
                "<p><strong>Gender:</strong> " + gender + "</p>" +
                "<p><strong>Account Creation Date:</strong> " + formattedCurrentDate + "</p>" +
                "</div></body></html>";
    }
}

