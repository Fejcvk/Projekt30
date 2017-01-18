package Login.controller;

import Login.repository.UserRepository;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;



@Controller
public class GetEmailController {
    private UserRepository userRepository;
    private UserService userService;
    private JavaMailSender javaMailSender;

    @Autowired
    public GetEmailController(UserRepository userRepository,
                              UserService userService, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }


    @RequestMapping("/getemail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("getemail", "error", error);

    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ModelAndView getRedirect(@RequestParam Optional<String> error, @RequestParam String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        Random gen = new Random();
        CharSequence code;
        code = Float.toString(gen.hashCode());
        userService.getUserByEmail(email).
                orElseThrow((() -> new NoSuchElementException(String.format(email))))
                .setPasswordHash(new BCryptPasswordEncoder().encode(code));

        userRepository.save(userService.getUserByEmail(email).
                orElseThrow((() -> new NoSuchElementException(String.format(email))))
        );

        mail.setTo(email);
        mail.setFrom("dropboxjavaproject@gmail.com");
        mail.setSubject("Password reset");
        mail.setText("Your new password is "+code);
        javaMailSender.send(mail);

        return new ModelAndView("redirect:/newpass","error",error);

    }

    @RequestMapping(value = "/newpass", method = RequestMethod.GET)
    public ModelAndView goHomePage(@RequestParam Optional<String> error) {
       return new ModelAndView("newpass", "error", error);
    }

    @RequestMapping("/redirect")
    public ModelAndView goHome(@RequestParam Optional<String> error) {
        return new ModelAndView("redirect:/", "error", error);

    }



}
