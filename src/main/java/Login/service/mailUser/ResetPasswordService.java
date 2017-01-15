package Login.service.mailUser;

import Login.repository.UserRepository;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


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

}
