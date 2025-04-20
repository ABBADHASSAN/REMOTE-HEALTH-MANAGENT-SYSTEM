import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmergencyAlert {
    private String patientId;
    private String alertMessage;
    private boolean isCritical;
    private String patientName;

    public EmergencyAlert(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
    }

    public void checkVitals(double heartRate, double bloodPressure, double oxygenLevel) {
        if (heartRate > 120 || heartRate < 50) {
            triggerAlert("Critical heart rate: " + heartRate);
        } else if (bloodPressure > 140 || bloodPressure < 90) {
            triggerAlert("Critical blood pressure: " + bloodPressure);
        } else if (oxygenLevel < 92) {
            triggerAlert("Critical oxygen level: " + oxygenLevel);
        }
    }

    private void triggerAlert(String message) {
        this.alertMessage = message;
        this.isCritical = true;
        sendEmailAlert(message);
    }

    public void sendEmailAlert(String alert) {
        String to = "mhassan.bsai24seecs@seecs.edu.pk";
        String from = "ibadhassan40@gmail.com";
        String password = "cryoqayyigyoxxuz";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("EMERGENCY ALERT!");
            message.setText("Dear Doctor,\n\n" +
                "This is an emergency alert for patient: " + patientName + " (ID: " + patientId + ")\n" +
                "Alert Message: " + alert + "\n\n" +
                "Please take immediate action.\n\n" +
                "Best regards,\n" +
                "Emergency Alert System");
            message.setSentDate(new java.util.Date());
            message.setHeader("X-Mailer", "JavaMail");

            Transport.send(message);
            System.out.println("✅ Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getPatientId() {
        return patientId;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public boolean isCritical() {
        return isCritical;
    }

    // Main method to test the functionality
    
}

class PanicButton {
    private String patientId;
    private String patientName;

    public PanicButton(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
    }

    public void press() {
        EmergencyAlert emergencyAlert = new EmergencyAlert(patientId, patientName);
        emergencyAlert.sendEmailAlert("Emergency! Panic button pressed for patient: " + patientName);
    }
}
