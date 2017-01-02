package Application.Controller;

import Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tomasz on 30.12.2016.
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
        this.userService = userService;

    @RequestMapping("/users")
    public ModelAndView getUserPage()
    {
        return new ModelAndView("user", "user", userService.getAllUsers());
    }
}
