package configuration;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailConfiguration {

    private Properties properties;
    private String username;
    private String password;

    public MailConfiguration() {
        prepareConfiguration();
    }

    private void prepareConfiguration() {
        properties = new Properties();
        try (FileReader reader = new FileReader("mail.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = properties.getProperty("mail.username");
        password = properties.getProperty("mail.password");


    }


    public Session createSession() {
        return Session.getDefaultInstance(properties, new GmailAuthenticator(username, password));
    }


}
