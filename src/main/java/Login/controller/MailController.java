package Login.controller;

import Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by filip on 12.01.17.
 */

@RestController
public class MailController {


    //To jest wszystko do zmiany ale nie wiem jak to kurwa ogarnąć
    String host = "pop.gmail.com";
    String mailStoreType = "pop3";
    String username = "zalogagpw@gmail.com";
    String password = "loncjemaslo";
    String port = "995";


    @RequestMapping("/gmail")
    public void readEmail() throws MessagingException, IOException {
        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);

        //create the POP3 store object and connect with the pop server
        Store store = emailSession.getStore("pop3s");

        store.connect(host, username, password);

        //create the folder object and open it
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);

        // retrieve the messages from the folder in an array and print it
        Message[] messages = emailFolder.getMessages();
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());

        }
        emailFolder.close(false);
        store.close();
    }
}
