package app.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService{

        @Autowired
        private JavaMailSender mailSender;


        @Override
        public void sendSimpleEmail(String to, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setText(text);
            message.setFrom("noreply@example.com");
            mailSender.send(message);
        }

}
