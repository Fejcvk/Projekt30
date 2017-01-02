package chujkurwa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz on 29.12.2016.
 */
@Controller
public class chujkontroler {
    @RequestMapping("/")
    String index()
    {
        return "home";
    }
}
