//Klasa do obsługi Maila

package vs.api.helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public static final String ADMIN_MAIL = "virtualspace@virtualspace.cba.pl";
    private static final String ADMIN_HOST = "mail.cba.pl";
    private static final String ADMIN_PASSWORD = "Virtualspace1";
    private String subject;
    private String content;
    private String recipient;

    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    //Metoda do wysyłania maila
    public void send() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", ADMIN_HOST);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ADMIN_MAIL, ADMIN_PASSWORD);
                    }
                });

        if (recipient != null && subject != null && content != null) {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ADMIN_MAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject, "utf-8");
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);
        } else throw new IllegalArgumentException("Can't create message with null arguments");

        throw new IllegalArgumentException("Mail sent.");
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
