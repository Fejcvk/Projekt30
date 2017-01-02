package logowanie.controller;

import logowanie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tomasz on 27.12.2016.
 */
@Controller
public class HelloController {

    @Autowired
    AdminService adminService;

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

    @RequestMapping("/admin")
    String admin()
    {
        adminService.ensureAdmin();
        return "admin";
    }
}
