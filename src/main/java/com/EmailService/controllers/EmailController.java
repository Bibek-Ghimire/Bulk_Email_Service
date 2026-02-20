package com.EmailService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.EmailService.services.EmailService;

@Controller
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/home")
    public String Home(){
        return "home";
    }

    @GetMapping("/compose")
    public String composeEmail() {
        return "send-email";
    }

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) throws Exception {

        emailService.sendDynamicEmail(subject, message, file);

        model.addAttribute("success", "Email sent successfully");
        return "send-email";
    }
}
