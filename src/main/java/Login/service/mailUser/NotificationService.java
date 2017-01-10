package Login.service.mailUser;

/**
 * Created by karol on 29.12.2016.
 */
import Login.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(User user) throws MailException{
        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        Random gen = new Random();
        long code;
        code = gen.hashCode();
        mail.setTo(user.getEmail());
        mail.setFrom("dropboxjavaproject@gmail.com");
        mail.setSubject("Registration Confirmation");
        mail.setText("Registered user with email "+user.getEmail()+ ". Your hash Code:"+code);
        javaMailSender.send(mail);
    }


}
