package com.example.rpms.model;

public class EmergencyAlert {
    public static void check_vitals (String name ,VitalSign vitalSign) {

        if (vitalSign.getHeartRate() > 100 || vitalSign.getBloodPressure() > 140 || vitalSign.getOxygenLevel() < 90 || vitalSign.getTemperature() > 38) {
            System.out.println("Emergency Alert: Patient " +name + " is in critical condition!");
            EmailNotification notification = new EmailNotification("mhassan.bsai24seecs@seecs.edu.pk");
            notification.sendNotification("Emergency Alert: Patient " +name + " is in critical condition! Please check immediately.");

            System.out.println("Emergency Alert: Patient " +name + " is in critical condition! Please check immediately.");
            // Send email notification to the doctor

        } else {
            System.out.println("Patient " +name + " is stable.");
        }

    }
}
