package com.example.rpms.model;

import java.util.ArrayList;

public class Patient extends User {
    // attributes specific to the Patient class
    private ArrayList<VitalSign> vitalsDatabase;  // to store the vital signs of the patient
    private ArrayList<Feedback> feedbacks;  // to store previous feedbacks given by doctors

    // constructor to initialize the Patient object
    public Patient(String id, String name, String email) {
        super(id, name, email);
        // new vitals database object for each patient4
        this.vitalsDatabase = new ArrayList<>();
        // new arraylist for feedbacks for each patient
        this.feedbacks = new ArrayList<>();
    }

    // getter for vitalsdatabase of each patient
    public void getVitals() {
        System.out.println("Vital Signs for " + getName() + ":");
        for (VitalSign v : vitalsDatabase) {
            System.out.println(v.toString());
        }
    }

    // no setters because doesnt make sense to change vitasldatabase and feedbacks after the patient has been created

    // for uploading a new vital sign
    public void uploadVitalSign(VitalSign vital) {
        vitalsDatabase.add(vital);
        System.out.println("Vital sign added for patient: " + getName());
    }

    // requesting a new appointment
    public void requestAppointment(Appointment appointment) {
        AppointmentManager.requestAppointment(appointment);
        System.out.println("Appointment requested for: " + getName());
    }
    // adding a new feedback
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback added for patient: " + getName());
    }



    // viewing previous feedbacks
    public void viewPreviousFeedbacks() {
        System.out.println("Feedbacks for " + getName() + ":");
        for (Feedback f : feedbacks) {
            System.out.println(f.getComments());
        }
    }
    // viewing previous feedbacks
    public void viewPreviousVitals(){
        System.out.println("Vital Signs for " + getName() + ":");
        for (VitalSign v : vitalsDatabase) {
            System.out.println(v.toString());
        }
    }

    // print feedbacks
    public void getFeedbacks(String doctorName) {
        System.out.println("Feedbacks from Dr. " + doctorName + ":");
        System.out.println("Feedbacks for " + getName() + ":");
        for (Feedback feedback : feedbacks) {
            if(feedback.getDoctorName().equals(doctorName)) {
                System.out.println(feedback.getComments());
            }

        }
    }

    // no toString bcs user's can be used. no need to didplay vitals and feedbacks in this
}
