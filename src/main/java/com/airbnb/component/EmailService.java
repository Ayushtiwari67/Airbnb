package com.airbnb.component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String toEmail,
                                        String subject,
                                        String text,
                                        File attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom("at1354296@gmail.com");
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);

        // Attachment handling

       if (attachment!=null) {
           messageHelper.addAttachment(attachment.getName(), attachment);
       }

        javaMailSender.send(mimeMessage);
    }
}
