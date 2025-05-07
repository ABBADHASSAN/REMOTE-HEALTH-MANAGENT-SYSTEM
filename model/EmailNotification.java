package com.example.rpms.model;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailNotification  {
    private String emailAddress;
    private String fromEmail = "ibadhassan40@gmail.com";
    private String password = "cryoqayyigyoxxuz";

    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public  void sendNotification(String message) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(fromEmail));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            email.setSubject("Notification Alert");
            email.setText(message);

            Transport.send(email);
            System.out.println("✅ Email sent successfully to " + emailAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
