
package Login.controller;

/**
 * Created by Tomasz on 18/01/2017.
 */
public class GmailController {
    //package Login.controller;
//package Login.controller;
//
//import Login.Application;
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.GmailScopes;
//import com.google.api.services.gmail.model.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by Tomasz on 11.01.2017.
// */
//@RestController
//public class GmailController {
//
//    private static final String APPLICATION_NAME = "Gmail API Interface";
//    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),".credentials/gmail-java-interface");
//
//    private static FileDataStoreFactory DATA_STORE_FACTORY;
//
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//
//    private static HttpTransport HTTP_TRANSPORT;
//
//
//    private static final List<String> SCOPES = Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);
//
//
//
//    static {
//        try {
//            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//        } catch (Throwable t) {
//            t.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//
//    public static Credential authorize() throws IOException
//    {
//        InputStream in = Application.class.getResourceAsStream("/client_secret.json");
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(DATA_STORE_FACTORY)
//                .setAccessType("offline")
//                .build();
//        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
//        return credential;
//    }
//
//
//    public static Gmail getGmailService() throws IOException
//    {
//        Credential credential = authorize();
//        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY,credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    @RequestMapping("/gmail")
//    public static void GmailStart() throws IOException {
//        Gmail service = getGmailService();
//
//        String user = "me";
//        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//        List<Label> labels = listResponse.getLabels();
//
//        if (labels.size() == 0) {
//            System.out.println("No labels found.");
//        } else {
//            System.out.println("Labels:");
//            for (Label label : labels) {
//                System.out.printf("- %s\n", label.getName());
//            }
//
//
//
//        }
////        service.users().messages().list(user).setQ("from:dropboxjavaproject@gmail.com");
////       ListMessagesResponse messagesResponse = service.users().messages().list(user).execute();
////        List<Message> messages = messagesResponse.getMessages();
////
////        for(Message message : messages)
////        System.out.println(message.getRaw());
//
//    }
//}
