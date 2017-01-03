package Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class GetEmailController {
//    private final UserService userService;
//    private final UserCreateFormValidator userCreateFormValidator;
//
//    @InitBinder("form")
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(userCreateFormValidator);
//    }
String dupa2;

    @RequestMapping("/getemail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("getemail", "error", error);
    }
}
