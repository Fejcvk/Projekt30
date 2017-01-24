package Login.controller;

import com.sun.mail.imap.IMAPFolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by filip on 12.01.17.
 */

@RestController
public class MailController {


    @RequestMapping("/gmail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("mail", "error", error);

    }

    @RequestMapping(value = "/abc", method = RequestMethod.GET)
    public ModelAndView readEmail(@RequestParam Optional<String> error,
                                  @RequestParam String email,@RequestParam String password) throws MessagingException, IOException {


        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flags.Flag flag = null;
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);

        store = session.getStore("imaps");
        store.connect("imap.googlemail.com",email, password);


        //create the folder object and open it
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);

        // retrieve the messages from the folder in an array and print it
        Message[] messages = emailFolder.getMessages();
        String[] name = new String[messages.length];
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            name[i]="";

            String contentType = message.getContentType();
            if (contentType.contains("multipart")) {
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    List<File> attachments = new ArrayList<File>();
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        name[i] = part.getFileName();

//                        InputStream is = part.getInputStream();
//                        File f = new File("C:\\Att/" +i + part.getFileName());
//                        FileOutputStream fos = new FileOutputStream(f);
//                        byte[] buf = new byte[4096];
//                        int bytesRead;
//                        while((bytesRead = is.read(buf))!=-1) {
//                            fos.write(buf, 0, bytesRead);
//                        }
//                        fos.close();
//                        attachments.add(f);
//
//                        System.out.println("---------------------------------");
//                        System.out.println("Email Number " + (i + 1));
//                        System.out.println("Subject: " + message.getSubject());
//                        System.out.println("From: " + message.getFrom()[0]);
//                        System.out.println("Text: " + message.getContent().toString());
//                        System.out.println("Att: "+part.getFileName());

                    }
                }
            }
        }
        emailFolder.close(false);
        store.close();



        return new ModelAndView("listofmails","name", name);
}


}


