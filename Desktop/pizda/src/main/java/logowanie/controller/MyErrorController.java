package logowanie.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * Created by Tomasz on 29.12.2016.
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @Autowired
    private ErrorAttributes errorAttributes;

    public String getErrorPath()
    {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public String error(HttpServletRequest request, Model model)
    {
        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false);
        model.addAttribute("errors", errorMap);
        return "error";
    }
}
