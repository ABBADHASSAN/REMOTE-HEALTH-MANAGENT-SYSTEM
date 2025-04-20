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
    public static void main(String[] args) {
        // Create a scanner for user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("=== Emergency Alert System Test ===");
        System.out.println("1. Test Emergency Alert with Vitals");
        System.out.println("2. Test Panic Button");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Test Emergency Alert with vitals
            System.out.print("Enter patient ID: ");
            String patientId = scanner.nextLine();

            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            EmergencyAlert alert = new EmergencyAlert(patientId, patientName);

            System.out.print("Enter heart rate: ");
            double heartRate = scanner.nextDouble();

            System.out.print("Enter blood pressure: ");
            double bloodPressure = scanner.nextDouble();

            System.out.print("Enter oxygen level: ");
            double oxygenLevel = scanner.nextDouble();

            alert.checkVitals(heartRate, bloodPressure, oxygenLevel);

            if (alert.isCritical()) {
                System.out.println("Critical alert triggered: " + alert.getAlertMessage());
            } else {
                System.out.println("Patient vitals are normal.");
            }
        } else if (choice == 2) {
            // Test Panic Button
            System.out.print("Enter patient ID: ");
            String patientId = scanner.nextLine();

            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            PanicButton button = new PanicButton(patientId, patientName);
            System.out.println("Press ENTER to trigger panic button...");
            scanner.nextLine(); // Wait for enter

            System.out.println("Triggering panic button...");
            button.press();
            System.out.println("Panic alert sent!");
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
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
