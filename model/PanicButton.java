package com.example.rpms.model;

public class PanicButton {
    private NotificationService notificationService;
    public PanicButton(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    public  void triggerAlert(String patientname){
        System.out.println("Panic button triggered for " + patientname);
        System.out.println("Sending emergency alert...");
        EmailNotification emailNotification = new EmailNotification("mhassan.bsai24seecs@seecs.edu.pk");
        emailNotification.sendNotification("Emergency alert for " + patientname  + "  : \n Kindly report as soon as possible. \n \n Thank you.");
        System.out.println("Alert sent to hospital.");
    }
}
