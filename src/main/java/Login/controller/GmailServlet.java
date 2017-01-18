package Login.controller;

/**
 * Created by Tomasz on 11.01.2017.
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@RestController
public class GmailServlet {

    private static final String CLIENT_ID = "865379198029-joe2d9fmn5jn3l2km22kvou5d7m1eg4f.apps.googleusercontent.com";
    private final String CLIENT_SECRETS = "/client_secrets2.json";
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/gmail-java");
    private static final String RESPONSE_TYPE = "code";
    private static final String API_KEY = "AIzaSyAlMl5mxX0a7swwcgGfqi11b2jdAFOHQ8I";
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.GMAIL_READONLY);

    private static HttpTransport HTTP_TRANSPORT ;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                GmailServlet.class.getResourceAsStream("/client_secret2.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    public static Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("MINIBox")
                .build();
    }

    @RequestMapping("/gmail")
    public static void listuj(String [] args) throws IOException
    {
        Gmail service = getGmailService();

        String user = "me";

        ListMessagesResponse listMessagesResponse = service.users().messages().list(user).execute();


        Message message = service.users().messages().get("me","1593731f22d48d65").execute();

        List<Message> messages = listMessagesResponse.getMessages();



        if(messages.size() == 0)
        {
            System.out.println("chuj");
        }
        else
        {
            for (Message message1: messages)
            {
                System.out.println(message1.getId());
            }
        }

    }
}


//    class SigninServlet extends HttpServlet {
//        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//            StringBuilder oauthUrl = new StringBuilder().append("https://accounts.google.com/o/oauth2/auth")
//                    .append("?client_id=").append(CLIENT_ID)
//                    .append("&response_type=code")
//                    .append("&scope=").append(SCOPE)
//                    .append("&redirect_uri=http://localhost:8080/oauth2callback")
//                    .append("&state=this_can_be_anything_to_help_correlate_the_response%3Dlike_session_id")
//                    .append("&access_type=offline")
//                    .append("&approval_prompt=force");
//            resp.sendRedirect(oauthUrl.toString());
//        }
//    }
//
//    class CallbackServlet extends HttpServlet {
//        @Override
//        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            if (req.getParameter("error") != null) {
//                resp.getWriter().println(req.getParameter("error"));
//                return;
//            }
//
//            String code = req.getParameter("code");
//
//            String body = post("https://accounts.google.com/o/oauth2/token", ImmutableMap.<String, String>builder()
//                    .put("code", code)
//                    .put("client_id", CLIENT_ID)
//                    .put("client_secret", CLIENT_SECRETS)
//                    .put("redirect_uri", "http://localhost:8080/oauth2callback")
//                    .put("grant_type", "authorization_code").build());
//
//
//            JSONObject jsonObject;
//            try {
//                jsonObject = (JSONObject) new JSONParser().parse(body);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            throw new RuntimeException("Unable" + body);
//
//
//            String accesToken = (String) jsonObject.get("access_token");
//            req.getSession().setAttribute("access_token", accesToken);
//            String json = get(new StringBuilder("https://www.googleapis.com/oauth2/v1/userinfo?access_token=").append(accesToken).toString());
//            resp.getWriter().println(json);
//        }
//    }
//
//    public String get(String url) throws ClientProtocolException, IOException {
//        return execute(new HttpGet(url));
//    }
//
//    public String post(String url, Map<String, String> formParameters) throws ClientProtocolException, IOException {
//        HttpPost request = new HttpPost(url);
//
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//
//        for (String key : formParameters.keySet()) {
//            nvps.add(new BasicNameValuePair(key, formParameters.get(key)));
//        }
//
//        request.setEntity(new UrlEncodedFormEntity(nvps));
//
//        return execute(request);
//    }
//
//    private String execute(HttpRequestBase request) throws ClientProtocolException, IOException {
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpResponse response = httpClient.execute(request);
//
//        HttpEntity entity = response.getEntity();
//        String body = EntityUtils.toString(entity);
//
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
//        }
//
//        return body;
//    }
//}
        //        JsonFactory jsonFactory = new JacksonFactory();
//
//        HttpRequestInitializer requestInitializer = httpRequest -> {
//            httpRequest.setConnectTimeout(0);
//            httpRequest.setReadTimeout(0);
//        };
//        Gmail service = new Gmail(NET_HTTP_TRANSPORT,jsonFactory,requestInitializer);
//
//        Message message = service.users().messages().get("me","1593731f22d48d65").execute();
//        resp.setContentType("text/html");
//        resp.setStatus(200);
//        Writer writer = resp.getWriter();
//        writer.write("<ul>");
//            writer.write("<li>" + message.getSnippet() + "</li>");
//        writer.write("</ul>");
