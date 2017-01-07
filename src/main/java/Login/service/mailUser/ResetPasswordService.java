package Login.service.mailUser;

import Login.domain.User;
import Login.repository.UserRepository;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Random;


@Service
public class ResetPasswordService {

    private final UserService userService;
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;


    @Autowired
    public ResetPasswordService(UserService userService, UserRepository userRepository,JavaMailSender javaMailSender) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }





    public void resetPassword(String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        Random gen = new Random();
        CharSequence code;
        code = Float.toString(gen.hashCode());
        User user = userRepository.findOneByEmail("dropboxjavaproject@gmail.com").get();
        userService.getUserByEmail("dropboxjavaproject@gmail.com").
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found","dropboxjavaproject@gmail.com"))))
                .setPasswordHash(new BCryptPasswordEncoder().encode(code));

        userRepository.save(userService.getUserByEmail(email).
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found","dropboxjavaproject@gmail.com"))))
        );

        mail.setTo("dropboxjavaproject@gmail.com");
        mail.setFrom("dropboxjavaproject@gmail.com");
        mail.setSubject("Password reset");
        mail.setText("Your new password is "+code);
        javaMailSender.send(mail);
    }
}
