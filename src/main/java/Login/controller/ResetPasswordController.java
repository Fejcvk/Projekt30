package Login.controller;

import Login.repository.UserRepository;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;



@Controller
public class ResetPasswordController {


    private UserRepository userRepository;
    private UserService userService;
    private JavaMailSender javaMailSender;

    @Autowired
    public ResetPasswordController(UserRepository userRepository, UserService userService, JavaMailSender javaMailSender) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.javaMailSender = javaMailSender;

    }


    @RequestMapping("/resetpassword/{id}")
    public ModelAndView getResetPasswordPage(@RequestParam Optional<String> error) {
        return new ModelAndView("resetpassword", "error", error);

    }

    @RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
    public ModelAndView resetPassword(@RequestParam Optional<String> error,@PathVariable long id,
                                      @RequestParam CharSequence passwordOld, @RequestParam String password,
                                      @RequestParam String passwordRepeated) {
        if(new BCryptPasswordEncoder().matches(passwordOld,userService.getUserById(id).
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found", id)))).getPasswordHash())) {
            if (password.equals(passwordRepeated)) {
                userService.getUserById(id).
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found", id))))
                        .setPasswordHash(new BCryptPasswordEncoder().encode(password));

                userRepository.save(userService.getUserById(id).
                        orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found", id)))));
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(userService.getUserById(id).
                        orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found", id)))).getEmail());
                mail.setFrom("dropboxjavaproject@gmail.com");
                mail.setSubject("New password");
                mail.setText("Your password has been changed. If you didn't changed it by yourself, reset it immediately");
                javaMailSender.send(mail);
                return new ModelAndView("redirect:/","error",error);
            }


        }

        return new ModelAndView("redirect:/resetpassword/"+id, "error", error);
    }

}
