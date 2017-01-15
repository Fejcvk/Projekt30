package Login.controller;

import Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by filip on 12.01.17.
 */

@RestController
public class MailController {


    //To jest wszystko do zmiany ale nie wiem jak to kurwa ogarnąć

    @RequestMapping("/gmail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("mail", "error", error);

    }

    @RequestMapping("/abc")
    public ModelAndView readEmail(@RequestParam Optional<String> error,
                                  @RequestParam String email,@RequestParam String password) throws MessagingException, IOException {


        String host = "pop.gmail.com";
        String mailStoreType = "pop3";
        String port = "995";
        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);

        //create the POP3 store object and connect with the pop server
        Store store = emailSession.getStore("pop3s");

        store.connect(host, email, password);

        //create the folder object and open it
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);

        // retrieve the messages from the folder in an array and print it
        Message[] messages = emailFolder.getMessages();
        for (int i = 0, n = messages.length; i < n; i++) {

            Message message = messages[i];
            String contentType = message.getContentType();
            if (contentType.contains("multipart")) {
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

                        System.out.println("---------------------------------");
                        System.out.println("Email Number " + (i + 1));
                        System.out.println("Subject: " + message.getSubject());
                        System.out.println("From: " + message.getFrom()[0]);
                        System.out.println("Text: " + message.getContent().toString());
                        System.out.println("Att: "+part.getFileName());

                    }
                }
            }
        }
        emailFolder.close(false);
        store.close();
        return new ModelAndView("redirect:/");
    }

}


