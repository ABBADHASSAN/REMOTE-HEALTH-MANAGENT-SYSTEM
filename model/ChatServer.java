package com.example.rpms.model;

public class ChatServer {
    public Patient patient;
    public Doctor doctor;
    public ChatServer(Patient patient, Doctor doctor) {
        this.patient =findPatientByName(patient.getName());
        this.doctor = findDoctorByName(doctor.getName());
        
    }
    public void send_patient_message( String message) {
        System.out.println("Sending message from " + patient.getName() + " to " + doctor.getName() + ": " + message);

    }
    public void send_doctor_message(String to, String message) {
        System.out.println("Sending message from " + doctor.getName() + " to " + patient.getName()+ ": " + message);

    }
    private Patient findPatientByName(String name) {
        for (Patient p : Administrator.getPatients()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        System.out.println("Patient not found in the hospital system.");
        return null;
    }
    private Doctor findDoctorByName(String name) {
        for (Doctor d : Administrator.getDoctors()) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        System.out.println("Doctor not found in the hospital system.");
        return null;
    }
}
