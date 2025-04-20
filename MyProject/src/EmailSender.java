
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class EmailSender {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the patient:");
        String name = input.nextLine();
        System.out.println("Enter the patient id:");
        String id = input.nextLine();


        System.out.println("Press 1 for emergency button or 0 for vitals check:");
        int emergency = input.nextInt();
        if (emergency == 1) {
            emergency_button(name,id);
            return;
        }
        else {

        System.out.println("Enter the heart rate of the patient:");
        int heartRate = input.nextInt();
        System.out.println("Enter the blood pressure of the patient:");
        int bloodPressure = input.nextInt();
        System.out.println("Enter the temperature of the patient:");
        int temperature = input.nextInt();

        if(heartRate > 100 || bloodPressure > 140 || temperature > 100) {
            System.out.println("The patient is in critical condition. Sending email alert...");
            send_email(name, heartRate, bloodPressure, temperature);
        } else {
            System.out.println("The patient is stable. No email alert needed.");
        }
    }
    }
    public static void emergency_button(String name,String id) {

        send_email(name,id,0,0, 0);
        System.out.println("Emergency button pressed. Email alert sent to doctor.");
    }

    public static void send_email(String name, String id , int heartRate, int bloodPressure, int temperature) {
        String to = "mhassan.bsai24seecs@seecs.edu.pk";
        String from = "ibadhassan40@gmail.com";
        String password = "cryoqayyigyoxxuz";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
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
                    "The patient " + name + " is in critical condition.\n" +
                    "Patient ID: " + id + "\n\n" +
                    "Please find the vitals below:\n" +
                    "Heart Rate: " + heartRate + "\n" +
                    "Blood Pressure: " + bloodPressure + "\n" +
                    "Temperature: " + temperature + "\n\n" +
                    "Please take immediate action.\n\n" +
                    "Best regards,\n" +
                    "Your Health Monitoring System");

            Transport.send(message);
            System.out.println("✅ Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


// java -cp "..\lib\*;." EmailSender  -------- RUN THIS COMMAND IN CMD TO RUN THE PROGRAM to run the program
// to complile javac -cp "..\lib\*" EmailSender.java
