package Login.controller;

import Login.repository.UserRepository;

import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by karol on 17.01.2017.
 */
@Controller
public class SendAttController{

    private UserRepository userRepository;
    private UserService userService;
    private JavaMailSender javaMailSender;

    @Autowired
    public SendAttController( UserRepository userRepository,
        UserService userService, JavaMailSender javaMailSender){
            this.userRepository = userRepository;
            this.userService = userService;
            this.javaMailSender = javaMailSender;
        }
    @RequestMapping("/sendatt")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("/sendattachment", "error", error);

    }

    @RequestMapping(value = "/att", method = RequestMethod.GET)
    public ModelAndView getRedirect(@RequestParam Optional<String> error, @RequestParam String email,
                                    @RequestParam String path) {
        String to = email;
        String from = "dropboxjavaproject@gmail.com";
        final String username = "dropboxjavaproject@gmail.com";
        final String password = "dropboxjava";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Your file");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Sent via DropboxUltra app");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(path);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(path);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }




        return new ModelAndView("redirect:/");
    }

}
