package com.example.rpms.model;
import java.util.ArrayList;
import java.util.List;

// required imports

public class Doctor extends User {
    // arraylists of patients that are assigned to a Doctor
    private ArrayList<Patient> patients;
    private ArrayList<Feedback> feedbacks ; // arraylist of feedbacks for the doctor

    // constructor
    public Doctor(String id, String name, String email) {
        super(id, name, email);
        this.patients = new ArrayList<>();   // iniitializing the new arraylist of patients for each doctor
        this.feedbacks = new ArrayList<>();  // initializing the new arraylist of feedbacks for each doctor
    }
    public Doctor() {
        super("defaultId", "defaultName", "defaultEmail"); // Provide default values
        this.patients = new ArrayList<>();
    }
    // gettern for patients
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    // no setter for patients bcs doesnt make sense


    // adding a new patient
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient " + patient.getName() + " added to Dr. " + getName() + "'s list.");
    }



    // viewing appointments for doctors
    public void viewAppointments() {
        System.out.println("Appointments for Dr. " + getName() + ":");
        for (Appointment a : AppointmentManager.getAppointments()) {
            if (a.getDoctor().equals(this)) {
                System.out.println(a.getDate() + " - " + a.getStatus());
            }
        }
    }

    // viewing patients
    public void viewPatients() {
        System.out.println("Patients for Dr. " + getName() + ":");
        for (Patient p : patients) {
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Email: " + p.getEmail());
        }
    }

    // viewing patient feedbacks
    public void viewFeedbacks() {
        System.out.println("Feedbacks for Dr. " + getName() + ":");
        for (Feedback f : feedbacks) {
            System.out.println(f.toString());
        }
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback added for Dr. " + getName() + ": " + feedback.getComments());
    }

    // viewing patient vitals
    public void viewPatientVitals(Patient patient) {
        System.out.println("Vitals for " + patient.getName() + ":");
        patient.getVitals();
    }

    // approving appointments
    public void approveAppointment(Appointment appointment) {
        AppointmentManager.approveAppointment(appointment);
        System.out.println("Appointment approved for: " + appointment.getPatient().getName());
    }

    // cancellign appointments
    public void cancelAppointment(Appointment appointment) {
        AppointmentManager.cancelAppointment(appointment);
        System.out.println("Appointment cancelled for: " + appointment.getPatient().getName());
    }
}
