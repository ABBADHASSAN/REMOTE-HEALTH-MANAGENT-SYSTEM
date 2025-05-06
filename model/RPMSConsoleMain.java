package com.example.rpms.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RPMSConsoleMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dummy Notifiable for notification services
        Notifiable notifier = new Notifiable() {
            public void sendNotification(String message, String recipient) {
                System.out.println("[Notification to " + recipient + "]: " + message);
            }
            public void notify(String message, String recipient) {
                System.out.println("[ALERT to " + recipient + "]: " + message);
            }
        };

        NotificationService notificationService = new NotificationService(notifier);
        ReminderService reminderService = new ReminderService(notifier);
        PanicButton panicButton = new PanicButton(notificationService);
        EmergencyAlert emergencyAlert = new EmergencyAlert();

        while (true) {
            System.out.println("\n=== Remote Patient Monitoring System ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Request Appointment");
            System.out.println("4. Approve Appointment");
            System.out.println("5. Trigger Panic Button");
            System.out.println("6. Upload Vital & Check Alert");
            System.out.println("7. Send Appointment Reminder");
            System.out.println("8. View Appointments");
            System.out.println("9. View Registered Doctors");
            System.out.println("10. View Registered Patients");
            System.out.println("11. View Vitals from File");
            System.out.println("12. View Appointments from File");
            System.out.println("13. Exit");

            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> addPatient();
                case 3 -> requestAppointment();
                case 4 -> approveAppointment();
                case 5 -> {
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    panicButton.triggerAlert(name);
                }
                case 6 -> {

                        Date currentDate = new Date();
                        System.out.println("Current Date: " + currentDate);
                        System.out.print("Enter patient name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter heart rate: ");
                        double heartRate = scanner.nextInt();
                        System.out.print("Enter blood pressure: ");
                        double bloodPressure = scanner.nextInt();
                        System.out.print("Enter oxygen level: ");
                        double oxygenLevel = scanner.nextInt();
                        System.out.print("Enter temperature: ");
                        double temperature = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        Patient patient = new Patient(name, " ", ""); // Dummy patient object
                        VitalSign vitalSign = new VitalSign(name,heartRate, bloodPressure, oxygenLevel, temperature,currentDate);
                        String line =  "name : " + name + "  Heart Rate : " + heartRate + " BP : " + bloodPressure + "Oxygen Level : " + oxygenLevel + "  Temprature : " + temperature;

                        try {

                            FileWriter fileWriter = new FileWriter("vitals.txt", true);
                            fileWriter.write(line + System.lineSeparator());

                            fileWriter.close();
                            emergencyAlert.check_vitals(name, vitalSign);
                        } catch (IOException ex) {
                            System.out.println("Error writing to file: " + ex.getMessage());
                        }


                }


                case 7 -> {
                    System.out.print("Enter patient name: ");
                    String patient = scanner.nextLine();
                    System.out.print("Enter appointment time: ");
                    String time = scanner.nextLine();
                    reminderService.sendAppointmentReminder(patient, time);
                }
                case 8 -> AppointmentManager.viewAppointments();

                case 9 -> {
                    System.out.println("\n--- Registered Doctors ---");
                    try (Scanner fileScanner = new Scanner(new java.io.File("doctors.txt"))) {
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading doctors.txt: " + e.getMessage());
                    }
                }

                case 10 -> {
                    System.out.println("\n--- Registered Patients ---");
                    try (Scanner fileScanner = new Scanner(new java.io.File("patients.txt"))) {
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading patients.txt: " + e.getMessage());
                    }
                }

                case 11 -> {
                    System.out.println("\n--- Patient Vitals ---");
                    try (Scanner fileScanner = new Scanner(new java.io.File("vitals.txt"))) {
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading vitals.txt: " + e.getMessage());
                    }
                }

                case 12 -> {
                    System.out.println("\n--- Appointment Requests ---");
                    try (Scanner fileScanner = new Scanner(new java.io.File("appointments.txt"))) {
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading appointments.txt: " + e.getMessage());
                    }
                }

                case 13 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void addDoctor() {

            System.out.print("Doctor ID: ");
            String id = scanner.nextLine();
            System.out.print("Doctor Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Specialization: ");
            String spec = scanner.nextLine();

            Doctor doc = new Doctor(id, name, email);
            Administrator.registerDoctor(doc);
            String line = "Doctor id : " + id + " Name : " + name + " Email : " + email + " Specialization : " + spec;

            try {
                FileWriter fileWriter = new FileWriter("doctors.txt",true);
                fileWriter.write(line + System.lineSeparator());
                fileWriter.close();
                System.out.println("Doctor added.");
            } catch (IOException ex) {
                System.out.println("Error writing to file: " + ex.getMessage());
            }



    }

    private static void addPatient() {

            System.out.print("Patient ID: ");
            String id = scanner.nextLine();
            System.out.print("Patient Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Patient pat = new Patient(id, name, email);
            Administrator.registerPatient(pat);
            String line = "Patient id : " + id + " Name : " + name + " Email : " + email;

            try {
                FileWriter fileWriter = new FileWriter("patients.txt", true);
                fileWriter.write(line + System.lineSeparator());

                fileWriter.close();
                System.out.println("Patient added.");

            } catch (IOException ex) {
                System.out.println("error occured while writing to file: " + ex.getMessage());
            }


    }

    private static void requestAppointment() {


            System.out.print("Enter doctor name: ");
            String doctorName = scanner.nextLine();
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();
            System.out.print("Enter appointment date (yyyy/MM/dd): ");
            String date = scanner.nextLine();
            Appointment appointment = new Appointment(date, doctorName, patientName);
            appointment.setStatus("Pending");
            try {
            String line = date + "," + doctorName + "," + patientName + "," + appointment.getStatus();
            FileWriter  fileWriter = new FileWriter("appointments.txt", true);
            fileWriter.write(line + System.lineSeparator());

            fileWriter.close();
            System.out.println("Appointment requested.");
            AppointmentManager.requestAppointment(appointment);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void approveAppointment() {
        ArrayList<Appointment> appointments = AppointmentManager.getAppointments();
        System.out.println("Pending Appointments:");
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            if (a.getStatus().equals("Pending")) {
                System.out.println((i + 1) + ". " + a.getDate() + " - " + a.getDoctor().getName() + " - " + a.getPatient().getName());
            }
        }

        System.out.print("Enter the number of the appointment to approve: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < appointments.size() && appointments.get(index).getStatus().equals("Pending")) {
            AppointmentManager.approveAppointment(appointments.get(index));
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
//             javac -cp "lib/jakarta.mail-2.0.1.jar;lib/jakarta.activation-2.0.1.jar" -d out src/main/java/com/example/rpms/model/*.java       -- to compile
//              java -cp "out;lib/jakarta.mail-2.0.1.jar;lib/jakarta.activation-2.0.1.jar" com.example.rpms.model.RPMSConsoleMain                -- to run
