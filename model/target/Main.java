public class Main {
    public static void main(String[] args) {
        // Replace with the recipient's email address
        String recipient = "mhassan.bsai24seecs@seecs.edu.pk";

        // Create EmailNotification instance
        EmailNotification notification = new EmailNotification(recipient);

        // Send a test message
        notification.sendNotification("This is a test notification from Java using Jakarta Mail.");
    }
}
