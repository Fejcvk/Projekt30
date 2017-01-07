package Login.controller;
import Login.service.mailUser.ResetPasswordService;
import Login.domain.User;
import Login.repository.UserRepository;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;

import java.io.IOException;

public class NewPasswordController {
    private UserService userService;
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;
    private ResetPasswordService resetPasswordService;

    @Autowired
    public NewPasswordController(UserService userService,
                                 UserRepository userRepository,JavaMailSender javaMailSender,
                                 ResetPasswordService resetPasswordService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
        this.resetPasswordService = resetPasswordService;
    }


    @RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
    public String resetPasswordView(final Model model) {
        model.addAttribute("user", new User());
        return "/forgetpassword";
    }
    @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
    public String forgetPassword(@ModelAttribute User user, final Model model) throws MessagingException, IOException {
        model.addAttribute("user", user);
        resetPasswordService.resetPassword(user.getEmail());

        return "/";
    }

}