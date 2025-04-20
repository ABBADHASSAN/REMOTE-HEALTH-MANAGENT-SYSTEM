// SMSNotification.java
public class SMSNotification implements Notifiable {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
        // Actual SMS sending logic would go here
    }
}
