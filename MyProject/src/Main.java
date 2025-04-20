// Main.java
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Remote Patient Monitoring System ===");
            System.out.println("1. Notification System");
            System.out.println("2. Emergency Alert System");
            System.out.println("3. Chat & Video Consultation");
            System.out.println("4. Exit");
            System.out.print("Select a module (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    testNotificationSystem();
                    break;
                case 2:
                    testEmergencyAlertSystem(scanner);
                    break;
                case 3:
                    testChatAndVideoSystem();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void testNotificationSystem() {
        // Create notifiers
        Notifiable emailNotifier = new EmailNotification("mhassan.bsai24seecs@seecs.edu.pk");
        Notifiable smsNotifier = new SMSNotification("+1234567890");

        // Test email notification
        ReminderService emailReminder = new ReminderService(emailNotifier);
        emailReminder.sendMedicationReminder("123-KLI-589", "Aspirin", "08:00 AM");

        // Test SMS notification
        ReminderService smsReminder = new ReminderService(smsNotifier);
        smsReminder.sendAppointmentReminder("564-HUJ-569", "Smith", "2023-12-15 10:00 AM");
    }

    private static void testEmergencyAlertSystem(Scanner scanner) {
        System.out.println("\n=== Emergency Alert System Test ===");
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
    }

    private static void testChatAndVideoSystem() {
        System.out.println("\n=== Chat & Video Consultation Test ===");

        // Create a chat server
        ChatServer server = new ChatServer();

        // Create clients
        ChatClient doctor = new ChatClient("dr_smith", "doctor", server);
        ChatClient patient = new ChatClient("john_doe", "patient", server);

        // Test chat functionality
        System.out.println("\nTesting Chat Functionality:");
        doctor.sendMessage("Hello John, how are you feeling today?");
        patient.sendMessage("I'm feeling much better today, thank you doctor!");

        // Test video call functionality
        System.out.println("\nTesting Video Call Functionality:");
        VideoCall call = new VideoCall("dr_smith", "john_doe", "https://meet.example.com/abc123");
        call.startCall();

        // Print chat history
        System.out.println("\nChat History:");
        List<String> history = server.getMessageHistory();
        for (String message : history) {
            System.out.println(message);
        }
    }
}
