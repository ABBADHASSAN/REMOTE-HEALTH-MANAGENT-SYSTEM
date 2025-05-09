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
        String userType ;
        do {

        System.out.println("Welcome to the Remote Patient Monitoring System!");
        System.out.println("Please enter your details to get started.");
        System.out.print("Enter your user type (lower-case) : ");
        System.out.println("enter exit to exit the function ");
         userType = scanner.nextLine();

        int choice ;
        if (userType.equals("admin")) {
            do {
                System.out.println("Welcome Admin!");
                System.out.println("1. Add Doctor");
                System.out.println("2. Add Patient");
                System.out.println("3. View Registered Doctors");
                System.out.println("4. View Registered Patients");
                System.out.println("5. View Appointments from File");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addPatient();
                    case 3 -> {
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
                    case 4 -> {
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
                    case 5 -> AppointmentManager.viewAppointments();
                    case 6 -> System.out.println("Exiting system. Goodbye!");
                    default -> System.out.println("Invalid option!");
                }
            } while (choice != 6);
        }


        else if (userType.equals("doctor")) {
            do {
                System.out.println("Enter your name :");
                String name = scanner.nextLine();
                Doctor doctor = Appointment.findDoctorByName(name);
                if (doctor == null) {
                    System.out.println("Doctor not found in the hospital system.");
                    return;
                }
            System.out.println("Welcome Doctor " + doctor.getName() + "!");
            System.out.println("1. Approve Appointment");
            System.out.println("2. Send Appointment Reminder");
            System.out.println("3. View Appointments");
            System.out.println("4. Add patient");
            System.out.println("5. View  Patients");
            System.out.println("6. Veiw feedbacks");
            System.out.println("7. Veiw Vitals");
            System.out.println("8. Send meeting link to patient");
            System.out.println("9. Write Prescription");
            System.out.println("10. Exit");

            System.out.print("Choose option: ");
             choice = scanner.nextInt();
            scanner.nextLine(); // consume newline


                switch (choice) {
                    case 1 -> approveAppointment();
                    case 2 -> {
                        System.out.print("Enter patient name: ");
                        String patient = scanner.nextLine();
                        System.out.print("Enter appointment time: ");
                        String time = scanner.nextLine();
                        ReminderService reminderService = new ReminderService(notifier);
                        reminderService.sendAppointmentReminder(patient, time);
                    }
                    case 3 -> AppointmentManager.viewAppointments();
                    case 4 -> {   ;}
                    case 5 -> {

                        doctor.viewPatients();
                    }
                    case 6 -> {

                        doctor.viewFeedbacks();

                    }
                    case 7 -> {
                        System.out.print("Enter your name: ");

                        System.out.print("Enter patient name: ");
                        String patientName = scanner.nextLine();
                        Patient patient = Appointment.findPatientByName(patientName);
                        if (patient == null) {
                            System.out.println("Patient not found in the hospital system.");
                            return;
                        }
                        doctor.viewPatientVitals(patient);
                    }
                    case 8 -> {


                        System.out.print("Enter patient name: ");
                        String patientName = scanner.nextLine();
                        System.out.print("Enter meeting link: ");
                        String meetingLink = scanner.nextLine();
                        Patient patient = Appointment.findPatientByName(patientName);
                        if (patient == null) {
                            System.out.println("Patient not found in the hospital system.");
                            return;
                        }
                        patient.setMeetingLink(meetingLink);
                    }
                    case 9 -> {
                        Appointment appointment = AppointmentManager.viewAppointments(name);
                        if (appointment == null) {
                            System.out.println("No approved appointments found for the doctor.");
                            return;
                        }
                        System.out.print("Enter medication: ");
                        String medication = scanner.nextLine();


                        Prescription prescription = new Prescription(medication, appointment);

                    }
                    default -> System.out.println("Invalid option!");
                }


            }
            while (choice != 10) ;
            System.out.println("Exiting system. Goodbye!");
        }

        // patient user
        else if (userType.equals("patient")) {
        do {
            System.out.println("Enter your name :");
            String name = scanner.nextLine();
            Patient patient = Appointment.findPatientByName(name);
            if (patient == null) {
                System.out.println("Patient not found in the hospital system.");
                return;
            }
            System.out.println("Welcome Patient " + patient.getName() + "!");
            System.out.println("1. Request Appointment");
            System.out.println("2. Trigger Panic Button");
            System.out.println("3. Upload Vital & Check Alert");
            System.out.println("4. View Appointments");
            System.out.println("5. Give Feedback");
            System.out.println("6. Get Meeting Link");
            System.out.println("7. View Vital Signs");
            System.out.println("8. View Medication");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");

            NotificationService notificationService = new NotificationService(notifier);
            ReminderService reminderService = new ReminderService(notifier);
            PanicButton panicButton = new PanicButton(notificationService);
            EmergencyAlert emergencyAlert = new EmergencyAlert();

             choice = scanner.nextInt();
            scanner.nextLine(); // consume newline


                switch (choice) {
                    case 1 -> {

                        requestAppointment();}
                    case 2 -> {
                        System.out.print("Enter patient name: ");

                        panicButton.triggerAlert(name);
                    }
                    case 3 -> {

                        System.out.print("Enter heart rate: ");
                        double heartRate = scanner.nextDouble();
                        System.out.print("Enter blood pressure: ");
                        double bloodPressure = scanner.nextDouble();
                        System.out.print("Enter oxygen level: ");
                        double oxygenLevel = scanner.nextDouble();
                        System.out.print("Enter temperature: ");
                        double temperature = scanner.nextDouble();

                        if (patient == null) {
                            System.out.println("Patient not found in the hospital system.");
                            return;
                        }
                        VitalSign vitalSign = new VitalSign(name,heartRate, bloodPressure, oxygenLevel, temperature,new Date());
                        String line =  "name : " + name + "  Heart Rate : " + heartRate + " BP : " + bloodPressure + "Oxygen Level : " + oxygenLevel + "  Temprature : " + temperature;

                        patient.uploadVitalSign(vitalSign);
                        EmergencyAlert.check_vitals(name,vitalSign);
                    }

                    case 4 -> {

                        AppointmentManager.viewAppointments(1);}

                    case 5 -> {
                        System.out.print("Enter your name: ");

                        System.out.print("Enter doctor name: ");
                        String doctorName = scanner.nextLine();
                        System.out.print("Enter Date: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter feedback: ");
                        String feedback = scanner.nextLine();
                        Feedback feedbackObj = new Feedback(date,feedback, doctorName, name);

                        Doctor doctor = Appointment.findDoctorByName(doctorName);
                        patient.addFeedback(feedbackObj);
                        doctor.addFeedback(feedbackObj);
                        String line = "Patient Name : " + name + " Doctor Name : " + doctorName + " Date : " + date + " Feedback : " + feedback;
                        try {
                            FileWriter fileWriter = new FileWriter("feedbacks.txt", true);
                            fileWriter.write(line + System.lineSeparator());

                            fileWriter.close();
                            System.out.println("Feedback added.");
                        } catch (IOException ex) {
                            System.out.println("Error writing to file: " + ex.getMessage());
                        }

                    }
                    case 6 -> {
                        System.out.print("Enter your name: ");

                        if (patient == null) {
                            System.out.println("Patient not found in the hospital system.");
                            return;
                        }
                        System.out.println("Meeting link: " + patient.getMeetingLink());
                    }
                    case 7 -> {
                        System.out.print("Enter your name: ");

                        if (patient == null) {
                            System.out.println("Patient not found in the hospital system.");
                            return;
                        }
                        patient.viewPreviousVitals();
                    }
                    case 8 -> {
                        Prescription.viewAppointments(name);
                    }
                    default -> System.out.println("Invalid option!");
                }
                System.out.print("Choose option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            }
            while (choice != 9);
        }

        else {
            System.out.println("Invalid user type. Exiting.");
            return;
        }
    }while (userType!="exit");

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
            Administrator.getPatients(1);
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
            AppointmentManager.add_appointment(appointment);
            try {
            String line = date + "," + doctorName + "," + patientName + "," + appointment.getStatus();
            FileWriter  fileWriter = new FileWriter("appointments.txt", true);
            fileWriter.write(line + System.lineSeparator());

            fileWriter.close();
            System.out.println("Appointment requested.");
            System.out.println("Appointment added to queue. Waiting to be approved.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void approveAppointment() {
        System.out.println("Enter your name :");
        String name = scanner.nextLine();

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
    public void doc_add_patient() {
        System.out.println("Enter your name :");
        String name = scanner.nextLine();
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        Doctor a = Appointment.findDoctorByName(name);
        if (a == null) {
            System.out.println("Doctor not found in the hospital system.");
            return;
        }

// finding the patient by name and adding it in doctor by getting doctor object by name
        a.addPatient(Appointment.findPatientByName(patientName));

        System.out.println("Patient added.");
    }
}




//             javac -cp "lib/jakarta.mail-2.0.1.jar;lib/jakarta.activation-2.0.1.jar" -d out src/main/java/com/example/rpms/model/*.java       -- to compile
//              java -cp "out;lib/jakarta.mail-2.0.1.jar;lib/jakarta.activation-2.0.1.jar" com.example.rpms.model.RPMSConsoleMain                -- to run
