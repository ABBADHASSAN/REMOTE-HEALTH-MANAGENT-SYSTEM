package com.example.rpms.model;
import java.util.Scanner;

import java.util.ArrayList;

public class AppointmentManager {
    // static arraylist to hold all appointments
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    // getter for appointments
    public static ArrayList<Appointment> getAppointments() {
        return appointments;


    }

    public static void add_appointment (Appointment a){

        appointments.add(a);
    }
    // viewing all appointments


// overloading to get a seperate working for patients
public static void viewAppointments(int a){
    Scanner scanner = new Scanner (System.in);
    System.out.println("Enter your name :");
    String name = scanner.nextLine();
    System.out.println("Appointments:");
    for (Appointment b : appointments) {
        if(name.equals(b.getPatient().getName()))
        System.out.println(b.getDate() + " - " + b.getStatus() + " - " + b.getDoctor().getName() + " - " + b.getPatient().getName());
    }
}

public static Appointment viewAppointments(String a){
    Scanner scanner = new Scanner (System.in);
    System.out.println("Enter your patient's name :");
    String name = scanner.nextLine();
    System.out.println("Appointments:");
    for (Appointment b : appointments) {
        if(name.equals(b.getPatient().getName()) && b.getStatus().equals("Approved")&& b.getDoctor().getName().equals(a))
       return b;
    }
    return null;
}
    public static void viewAppointments(){
        Scanner scanner = new Scanner (System.in);
        System.out.println("Enter your name :");
        String name = scanner.nextLine();
        System.out.println("Appointments:");
        for (Appointment a : appointments) {
            if(name.equals(a.getDoctor().getName()))
            System.out.println(a.getDate() + " - " + a.getStatus() + " - " + a.getDoctor().getName() + " - " + a.getPatient().getName());
        }
    }
    // viewing pending appointments
    public static void viewPendingAppointments() {
        System.out.println("Pending Appointments:");
        for (Appointment a : appointments) {
            if (a.getStatus().equals("Pending")) {
                System.out.println(a.getDate() + " - " + a.getDoctor().getName() + " - " + a.getPatient().getName());
            }
        }
    }

    // viewing approved appointments
    public static void viewApprovedAppointments() {
        System.out.println("Approved Appointments:");
        for (Appointment a : appointments) {
            if (a.getStatus().equals("Approved")) {
                System.out.println(a.getDate() + " - " + a.getDoctor().getName() + " - " + a.getPatient().getName());
            }
        }
    }

    // viewing cancelled appointments


    // requesting a new appointment
    public static void requestAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment added to queue. Waiting to be approved.");
    }

    // approving an appointment
    public static void approveAppointment(Appointment appointment) {
        appointment.setStatus("Approved");
        System.out.println("Appointment approved: " + appointment.getDate());
    }

    // cancelling an appointment
    public static void cancelAppointment(Appointment appointment) {
        appointment.setStatus("Cancelled");
        System.out.println("Appointment cancelled: " + appointment.getDate());
    }

}
