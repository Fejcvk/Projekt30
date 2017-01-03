package Login.service.mailUser;

import Login.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Tomasz on 03.01.2017.
 */
@Service
public class NewPasswordService {


    private JavaMailSender javaMailSender;

    @Autowired
    public NewPasswordService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

}
