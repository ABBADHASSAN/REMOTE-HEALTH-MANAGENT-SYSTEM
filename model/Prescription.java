package com.example.rpms.model;
import java.util.ArrayList;
import java.util.Scanner;
public class Prescription {
    // required attributes for prescription
    private static ArrayList<Prescription> prescriptions = new ArrayList<>();
    private String medication;
    private Appointment appointment;
    // constructor
    public Prescription(String medication,Appointment appointment) {
        this.medication = medication;
        this.appointment = appointment;
        appointment.setStatus("Completed");
        prescriptions.add(this);
    }
    public  static void viewAppointments(String patientName) {
        System.out.println("Medication For Appointments:");
        boolean found = false;
        for (Prescription prescription : prescriptions) {
            if (prescription.getAppointment().getPatient().getName().equals(patientName) &&
                prescription.getAppointment().getStatus().equals("Completed")) {
                // Print the toString result
                System.out.println(prescription.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No completed appointments found for  " + patientName + ".");
        }
    }
public Appointment getAppointment() {
    return appointment;
}

public String getMedication() {
    return medication;
}

    @Override
public String toString() {
    return "Prescription{" +
            "medication='" + medication + '\'' +
            ", appointment=" + appointment.toString() +
            '}';
}

}
