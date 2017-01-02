package logowanie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz on 27.12.2016.
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    String home()
    {
        return "home";
    }
    @RequestMapping("/restricted")
    String restricted()
    {
        return "restricted";
    }
}
