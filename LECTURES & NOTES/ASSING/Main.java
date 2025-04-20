
import java.util.ArrayList;
import java.util.List;

class user {
    private  String name ;
    private  String phone_no ;
    private  String email ;
    private  String DOB ;
            private  String address ;
                                                //constructor
    user( String name,String phone_no ,String email ,String DOB ,  String address )
    {
        this.name = name ;
        this.phone_no = phone_no ;
        this.email = email ;
        this.DOB = DOB ;
        this.address = address ;
      }

                                               // getters


   public String getName() { return name; }
public String getPhone_no() { return phone_no; }
public String getEmail() { return email; }
public String getDOB() { return DOB; }
public String getAddress() { return address; }


 protected  void display_info (){
    System.out.println("Name: " + name);
    System.out.println("Phone No: " + phone_no);
    System.out.println("Email: " + email);
    System.out.println("Date of Birth: " + DOB);
    System.out.println("Address: "  + address);

 }
//end class user
}

class patient extends user {

    private  vitals  vitals ;
    private  ArrayList<appointment> appointment ;
    private  ArrayList<feedback> feedback ;
    private  String patient_id ;



    patient (String patient_id, String name,String phone_no ,String email ,String DOB ,  String address , vitals vitals)

    {
        super (name, phone_no, email, DOB, address);
        this.patient_id = patient_id ;
        this.vitals = vitals ;
        this.feedback = new ArrayList<>(); // to create a feedback object for every new patient
        this.appointment = new ArrayList<>(); // creating list of appointments for every new patient
    }

     public void addvitals(double heart_rate , double oxygen_level)
     {
        this.vitals = new vitals(this.patient_id,heart_rate, oxygen_level);
     }


     public void veiw_vitals(){
        System.out.println("Patient ID: " + patient_id);
        System.out.println("Heart Rate: " + vitals.getHeartRate());
        System.out.println("Oxygen Level: " + vitals.getOxygenLevel());
     }

     public void give_feedback (feedback txt){
        feedback.add(txt);
     }

     public void check_feedback() {
        if (feedback.isEmpty()) {
            System.out.println("No feedback available for " + getName());
            return;
        }

        System.out.println("Feedback for " + getName() + ":");
        for (feedback feedback : feedback) {
            feedback.display();

        }
    }
    //end of class patient
}

class doctor extends user {
    private String specialize;
    private  ArrayList<appointment> appointment ;
    doctor (String specialize , String name,String phone_no ,String email ,String DOB ,  String address) {
        super (name, phone_no, email, DOB, address);
        this.specialize = specialize;
        this.appointment = new ArrayList<>(); // creating list of appointments for every new doctor

    }

      public void veiw_patient_info (patient p){
        System.out.println(p);
      }

    public void add_appointment(appointment a){
        this.appointment.add(a);
        System.out.println("\nAppointment added for: " + a.get_patient_id());
    }


    public void viewAppointments() {
            System.out.println("\n--- Scheduled Appointments ---");
            for (appointment appointment : appointment) {
                System.out.println(appointment);
            }
        }

        @Override
        public void display_info (){
            super.display_info();
            System.out.println("Specialization: " + specialize);
            System.out.println("Title : Doctor");
        }
    //end of class doctor
}


class admin extends user {

    private  ArrayList<doctor> doctor ;
    private  ArrayList<patient> patient ;
    private  ArrayList<String> systemlog;
    admin( String name,String phone_no ,String email ,String DOB ,  String address )
    {
        super (name,phone_no,email,DOB,address);
        this.doctor = new ArrayList<>(); // creating list of doctors
        this.patient = new ArrayList<>(); // creating list of patients
        this.systemlog = new ArrayList<>(); // creating list of system logs

      }


      public void add_doctor(doctor doctor){
        this.doctor.add(doctor);
        log_action("Doctor added: " + doctor.getName() );
        System.out.println("Doctor " + doctor.getName() + " added successfully.");
      }

      public void remove_doctor(doctor doctor){
        this.doctor.remove(doctor);
        log_action("Doctor removed: " + doctor.getName());
        System.out.println("Doctor " + doctor.getName() + " removed successfully.");
      }

      public void add_patient(patient patient){
        this.patient.add(patient);
        log_action("Patient added: " + patient.getName() );
        System.out.println("Patient " + patient.getName() + " added successfully.");
      }

      public void remove_patient(patient patient){
        this.patient.remove(patient);
        log_action("Patient removed: " +patient.getName());
        System.out.println("Patient " + patient.getName() + " removed successfully.");
      }


      private void log_action(String action) {
        systemlog.add(action);
    }

    @Override
    public void display_info() {
        super.display_info();
        System.out.println("Role: Administrator");
    }
    //end of class
    }






//interaction ----------------------------------------------------------------------


class medical_record {
    protected  String date ;
    protected  doctor doctor;
    protected  patient patient ;

    // constructor

    medical_record(String date , doctor doctor , patient patient) {
        this.date = date ;
        this.doctor = doctor ;
        this.patient = patient ;
    }

    public String getDate() { return date; }
    public doctor getDoctor() { return doctor; }
    public patient getPatient() { return patient; }
    public void setDate(String date) { this.date = date; }
    public void setDoctor(doctor doctor) { this.doctor = doctor; }
    public void setPatient(patient patient) { this.patient = patient; }


    public void display(){
        System.out.println("Medical Record:");
        System.out.println("Date: " + this.date);
        System.out.println("Doctor: " + this.doctor.getName());
        System.out.println("Patient: " + this.patient.getName());
    }


}


class prescription extends  medical_record {
private String med_name ;
private int dose ;
private String timing ;


//constructor

prescription (String date , doctor doctor , patient patient , String med_name , int dose , String timing ){
    super(date , doctor , patient );
    this.med_name = med_name ;
    this.dose = dose ;
    this.timing = timing ;
}
public String getMedName() { return med_name; }
public void setMedName(String medName) { this.med_name = medName; }

public int getDose() { return dose; }
public void setDose(int dose) { this.dose = dose; }

public String getTiming() { return timing; }
public void setTiming(String timing) { this.timing = timing; }
@Override
public void display(){
   super.display();
   System.out.println("Medicine Name: " + this.med_name);
   System.out.println("Dose: " + this.dose);
   System.out.println("Timing: " + this.timing);
}

}



class med_history {
    private  String patient_id ;
    public  ArrayList<medical_record> record ;


    //constructor

     med_history(String patient_id){
        this.patient_id = patient_id;
        this.record = new ArrayList<medical_record>();
    }


    //adding to the medical history

    public void add_record (medical_record r){
        this.record.add(r);
    }

    //dislayng whole record

    public void dispaly (){
        System.out.println("MEDICAL RECORD :");
        for (medical_record m : this.record){
                m.display();
        }
    }
}




class feedback extends  medical_record {
    private String feedback_txt ;
                // constructor
    feedback(String date , doctor doctor , patient patient, String feedback_txt){
        super(date, doctor, patient);
        this.feedback_txt = feedback_txt ;
    }

    public String get_feedback(){ return this.feedback_txt ; }

    @Override
    public void display (){
        super.display();
        System.out.println("Feedback : " + this.feedback_txt);
    }

}



//appointment ------------------------------------------------------------------------------------------------------



class vitals {  //vitals of the patient

    private String patient_id;
    private double heart_rate;
    private double oxygen_level;
                                    // constructor
    vitals (String patient_id , double heartRate , double oxygen_level ){
        this.patient_id = patient_id ;
        this.heart_rate = heartRate ;
        this.oxygen_level = oxygen_level ;
    }
                            //getters & setters
    public double getHeartRate() { return heart_rate; }
    public void setHeartRate(double heartRate) { this.heart_rate = heartRate; }
    public double getOxygenLevel() { return oxygen_level; }
    public void setOxygenLevel(double oxygenLevel) { this.oxygen_level = oxygenLevel; }



    public void display_vitals() {
        System.out.println("Patient ID: " + patient_id);
        System.out.println("Heart Rate: " + heart_rate);
    }


 }

 class appointment {
    private String appointment_id;
    private String patient_id;
    private String doctor_id;
    private String date;
    private String time;
    private String status;
                                                      // constructor
    appointment (String appointment_id, String patient_id, String doctor_id, String date, String time, String status){
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id=doctor_id;
        this.date = date;
        this.time = time;
        this.status = status ;
    }
                                                        // getter and setters
    public String get_appointment_id() { return appointment_id; }
    public String get_patient_id() { return patient_id; }
    public String get_doctor_id() { return doctor_id; }
    public String get_date() { return date; }
    public void set_date(String date) { this.date = date; }
    public String get_time() { return time; }
    public void set_time(String time) { this.time = time; }
    public String get_status() { return status; }
    public void set_status(String status) { this.status = status; }


    public void display_appointment() {
        System.out.println("Appointment ID: " + appointment_id);
        System.out.println("Patient ID: " + patient_id);
        System.out.println("Doctor ID: " + doctor_id);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
        }
 }

class  appointment_manager {
    private ArrayList<appointment> appointments;

    public appointment_manager() {
       this. appointments = new ArrayList<>();
    }

public void schedule_appointment(String appointment_id, String patient_id, String doctor_id, String date, String time)

    {
        appointment new_appointment = new appointment ( appointment_id,  patient_id,  doctor_id, date,  time, "SCHEDULED");

        appointments.add(new_appointment);
    }

                     // approve and cancel appointments by giving the doctor_id and patientid
    public void approve_appointment (String patient_id , String doctor_id) {
        for (appointment a : appointments) {
          if (a.get_patient_id().equals(patient_id) && a.get_doctor_id().equals(doctor_id)){
            a.set_status("SCHEDULED");
            System.out.println("Appointment approved");
          }
        }
    }

    public void cancel_appointment (String patient_id , String doctor_id){
        for (appointment a : appointments) {
            if (a.get_patient_id().equals(patient_id) && a.get_doctor_id().equals(doctor_id)){
                a.set_status("CANCELLED");
                System.out.println("Appointment cancelled");
            }
        }
    }


    public void display_all_appointment(){
        for (appointment a : appointments) {
            if (a.get_status().equals("SCHEDULED")) {
                a.display_appointment();
            }
        }
    }
    //end class
}

// main --------------------------------------------------------------------------------------------------
public class Main {
    public static void main(String[] args) {
        // Create admin
        admin hospitalAdmin = new admin("Admin Name", "1234567890", "admin@hospital.com", "1980-01-01", "Hospital Address");

        // Create doctors
        doctor cardiologist = new doctor("Cardiology", "Dr. Smith", "1112223333", "smith@hospital.com", "1975-05-15", "Hospital Wing A");
        doctor neurologist = new doctor("Neurology", "Dr. Johnson", "4445556666", "johnson@hospital.com", "1982-08-20", "Hospital Wing B");

        // Add doctors to system
        hospitalAdmin.add_doctor(cardiologist);
        hospitalAdmin.add_doctor(neurologist);

        // Create patients
        patient patient1 = new patient("P001", "John Doe", "7778889999", "john@email.com", "1990-04-25", "123 Main St", null);
        patient patient2 = new patient("P002", "Jane Smith", "2223334444", "jane@email.com", "1985-11-30", "456 Oak Ave", null);

        // Add patients to system
        hospitalAdmin.add_patient(patient1);
        hospitalAdmin.add_patient(patient2);

        // Create vitals for patients
        patient1.addvitals(72, 98.6);
        patient2.addvitals(85, 97.2);

        // Display patient vitals
        System.out.println("\n--- Patient Vitals ---");
        patient1.veiw_vitals();
        patient2.veiw_vitals();

        // Create appointment manager
        appointment_manager apptManager = new appointment_manager();

        // Schedule appointments
        apptManager.schedule_appointment("A001", "P001", "D001", "2023-12-15", "10:00");
        apptManager.schedule_appointment("A002", "P002", "D002", "2023-12-16", "11:00");

        // Approve and cancel appointments
        apptManager.approve_appointment("P001", "D001");
        apptManager.cancel_appointment("P002", "D002");

        // Display all scheduled appointments
        System.out.println("\n--- Scheduled Appointments ---");
        apptManager.display_all_appointment();

        // Doctor views appointments
        System.out.println("\n--- Doctor's View ---");
        cardiologist.viewAppointments();

        // Create medical feedback
        feedback cardiologyFeedback = new feedback("2023-12-10", cardiologist, patient1, "Patient recovering well, continue medication");

        // Patient receives feedback
        patient1.give_feedback(cardiologyFeedback);

        // View feedback
        System.out.println("\n--- Patient Feedback ---");
        patient1.check_feedback();

        // Create prescription
        prescription medPrescription = new prescription("2023-12-10", cardiologist, patient1, "Ibuprofen", 200, "Every 8 hours");

        // Display prescription
        System.out.println("\n--- Prescription Details ---");
        medPrescription.display();

        // Create medical history
        med_history patientHistory = new med_history("P001");
        patientHistory.add_record(cardiologyFeedback);
        patientHistory.add_record(medPrescription);

        // Display medical history
        System.out.println("\n--- Medical History ---");
        patientHistory.dispaly();

        // Display user information
        System.out.println("\n--- User Information ---");
        hospitalAdmin.display_info();
        cardiologist.display_info();
        patient1.display_info();
    }
}
