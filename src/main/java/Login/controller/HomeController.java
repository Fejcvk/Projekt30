package Login.controller;

/**
 * Created by karol on 27.12.2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {


    @RequestMapping("/")
    public String getHomePage() {
        return "home";
    }


}