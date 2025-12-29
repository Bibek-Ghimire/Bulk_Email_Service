package com.EmailService.services;

import java.io.IOException;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.EmailService.entities.Users;
import com.EmailService.repository.UserRepository;
import org.springframework.core.io.ByteArrayResource;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service

public class EmailService {

    private JavaMailSender mailSender;
    
    private UserRepository userRepository;

    public EmailService(JavaMailSender mailSender, UserRepository userRepository) { 
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void sendDynamicEmail(
            String subject,
            String messageText,
            MultipartFile file
    ) throws MessagingException, IOException {

        List<Users> users = userRepository.findAll();

        for (Users user : users) {


            String otp = String.valueOf((int)Math.floor(Math.random() * 900000) + 100000);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            String htmlContent =
                    "<h3>Hello " + user.getName() + "</h3>" +
                    "<p>" + messageText+ "</p>" +
                    "<p>Your OTP is: " + otp + "</p>";

            helper.setText(htmlContent, true);

            if (file != null && !file.isEmpty()) {
                helper.addAttachment(
                        file.getOriginalFilename(),
                        new ByteArrayResource(file.getBytes())
                );
            }

            mailSender.send(message);
        }
    }
}

