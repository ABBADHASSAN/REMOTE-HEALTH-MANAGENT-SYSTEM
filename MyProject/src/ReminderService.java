// ReminderService.java
public class ReminderService {
    private Notifiable notifier;

    public ReminderService(Notifiable notifier) {
        this.notifier = notifier;
    }

    public void sendMedicationReminder(String patientId, String medication, String time) {
        String message = "Reminder for " + patientId + ": Take " + medication + " at " + time;
        notifier.sendNotification(message);
    }

    public void sendAppointmentReminder(String patientId, String doctorId, String dateTime) {
        String message = "Reminder for " + patientId + ": You have an appointment with Dr. " +
                         doctorId + " at " + dateTime;
        notifier.sendNotification(message);
    }
}
