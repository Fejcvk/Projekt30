package Login.controller;

/**
 * Created by karol on 29.12.2016.
 */
import Login.domain.User;
import Login.service.mailUser.NotificationService;
import Login.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private final UserService userService;
    @Autowired
    private NotificationService notificationService;


    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/succes/{userEmail}/{id}")
    public ModelAndView getDropboxPage(@PathVariable String userEmail,@PathVariable long id) {

        LOGGER.debug("Getting dropbox page");

        User user = new User();
        user.setEmail(userEmail);
        user.setId(id);
        notificationService.sendNotification(user);
        return new ModelAndView("redirect:/dropbox/{id}");

    }
}