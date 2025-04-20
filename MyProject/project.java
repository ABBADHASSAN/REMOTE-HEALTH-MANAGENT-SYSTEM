


import java.util.ArrayList ;

abstract class team_member{
    private String name;
    private String id;
    private String gender ;
    private String join_date ;
    private final int max_hours = 12 ;
                        //parametarized constructor
    team_member (String name , String id , String gender , String join_date){
        this.name = name ;
        this.id = id ;
        this.gender = gender ;
        this.join_date = join_date ;
    }
                // getters because we cannot access private variables directly
    public String get_name(){ return name ;}
    public String get_id(){ return id ;}
    public String get_gender(){ return gender ;}
    public String get_join_date(){ return join_date ;}

    public void display (){
        System.out.println("Name: " + name + "is a " + gender + " and works " + max_hours + "hours .");
    }
}


  class patient {
    private String name ;
    private String DOB ;
    private String gender ;
    private String date_admitted;
    private String medical_report;
    private String days_in_hodpital;
    private doctor doctor ;
   //                       constructor
    patient(String name ,String DOB ,String gender ,String date_admitted , String medical_report ,String days_in_hodpital ,doctor doctor){
        this.name = name ;
        this.DOB = DOB ;
        this.gender = gender ;
        this.date_admitted = date_admitted ;
        this.medical_report = medical_report ;
        this.days_in_hodpital = days_in_hodpital ;
        this.doctor = doctor ;
    }
 //                     getters & setters
 public String get_name() { return name; }
public void set_name(String name) { this.name = name; }
public String get_DOB() { return DOB; }
public void set_DOB(String DOB) { this.DOB = DOB; }
public String get_gender() { return gender; }
public void set_gender(String gender) { this.gender = gender; }
public String get_date_admitted() { return date_admitted; }
public void set_date_admitted(String date_admitted) { this.date_admitted = date_admitted; }
public String get_medical_report() { return medical_report; }
public void set_medical_report(String medical_report) { this.medical_report = medical_report; }
public String getDays_in_hodpital() { return days_in_hodpital; }
public void setDays_in_hodpital(String days_in_hodpital) { this.days_in_hodpital = days_in_hodpital; }
public doctor get_doctor() { return doctor; }
public void set_dcoctor(doctor doctor) { this.doctor = doctor; }

  }

abstract  class doctor extends team_member {
    private String specialize ;
    private ArrayList<patient> patients;
//                          constructor
            doctor (String name , String id , String gender , String join_date , String specialize){
                super(name,id,gender,join_date);
                this.specialize = specialize ;
                this.patients = new ArrayList<patient>();
            }
//                      getter
public String get_specialize() { return specialize; }
        //                      method to add patient set respective doctor as the patient's doctor
            public void add_patient (patient patient) {
                this.patients.add(patient);
                 patient.set_dcoctor(this);
            }
//                          method to remove patient from doctor's list abd set that patient's doctor null
            public void remove_patient(patient patient){
                this.patients.remove(patient);
                patient.set_dcoctor(null);
            }

            public void veiw_medical_history(patient patient){
                patient .get_medical_report();
            }

            public abstract void treatPatient(patient patient);
            // making it abstract so that it can be implemented by the subclass
}


//                          senior doctor is a doctor so extends the doctor class
class senior_doctor extends doctor {
    senior_doctor(String name, String id, String gender, String join_date, String specialize) {
        super(name, id, gender, join_date, specialize);
    }
    @Override
    public void treatPatient(patient patient) {
        System.out.println("Senior doctor " + get_name() + " is treating patient " + patient.get_name());
    }
}


class surgeon extends doctor {
    surgeon(String name, String id, String gender, String join_date, String specialize) {
        super(name, id, gender, join_date, specialize);
    }
    @Override
    public void treatPatient(patient patient) {
        System.out.println("Surgeon " + get_name() + " is treating patient " + patient.get_name());
    }
}

class intern extends doctor {
    private   senior_doctor head_doctor;
    intern(String name, String id, String gender, String join_date, String specialize , senior_doctor head_doctor) {
        super(name, id, gender, join_date, specialize);
        this.head_doctor = head_doctor;
    }

    @Override
    public void treatPatient(patient patient) {
        System.out.println("Intern " + get_name() + " is treating patient " + patient.get_name() + " under the supervision of " + head_doctor.get_name());
    }
}
//              making nurse class by extending team_member class
class nurse extends team_member {
    nurse (String name , String id , String gender , String join_date){
        super(name,id,gender,join_date);
    }

    public void assists(doctor doctor){
        System.out.println("Nurse " + get_name() + " is assisting doctor " + doctor.get_name());
    }
}


class departments {
    private String name;
    private ArrayList<team_member> staff;
//          constructor
    departments (String name) {
        this.name = name;
        this.staff = new ArrayList <>();
    }

//          getters
public String get_name() { return name; }
public ArrayList<team_member> get_staff() { return staff; }

//      method to add staff members
public void add_staff(team_member staff_member) {
    this.staff.add(staff_member);
    System.out.println("Staff member added successfully");
}
//    method to remove a staff member
public void remove_staff(team_member staff_member) {
    if (staff_member != null) {
        this.staff.remove(staff_member);
    }
    else
    System.out.println("staff not present");
}

}
// making a hospital class which can control departments and patients


class hospital {
    private String name;
    private String address ;
    private ArrayList<departments> departments;
    private ArrayList<patient> patients;


//              constructor
hospital (String name , String address) {
    this.name = name;
    this.address = address;
    this.departments = new ArrayList<>();
    this.patients = new ArrayList<>();
}

// getters
public String get_name() { return name; }
public String get_address() { return address; }
public ArrayList<departments> get_departments() { return departments; }
public ArrayList<patient> get_patients() { return patients; }

// methods to add and remove patients & departments

public void add_patient (patient patient) {
    this.patients.add(patient);
}

public void remove_patient(patient patient){
    this.patients.remove(patient);
}

public void add_department (departments department) {
    this.departments.add(department);
}

public void remove_department(departments department) {
    this.departments.remove(department);

}
}


public class project {
    public static void main(String[] args) {
        // Creating a new hospital object
        hospital shifa = new hospital("Shifa International", "H-8/4 Islamabad");

        // Creating departments object
        departments ENT = new departments("ENT");
        departments Gynae = new departments("Gynecology");
        departments Cardiology = new departments("Cardiology");

        shifa.add_department(ENT);
        shifa.add_department(Gynae);
        shifa.add_department(Cardiology);

        // creating staff objects
        surgeon drAli = new surgeon("Dr. Ali Khan", "SUR-001", "male", "12/09/2018", "Cardiac Surgery");
        senior_doctor drAhad = new senior_doctor("Dr. Ahad Malik", "SEN-001", "male", "22/06/2015", "ENT");
        intern drFatima = new intern("Dr. Fatima Ahmed", "INT-001", "female", "21/01/2022", "Gynecology", drAhad);
        nurse nurseAmna = new nurse("Amna Khan", "NUR-001", "female", "12/09/2020");

        // Adding staff to departments
        ENT.add_staff(drAhad);
        ENT.add_staff(nurseAmna);
        Gynae.add_staff(drFatima);
        Cardiology.add_staff(drAli);

        // Creating patients objects
        patient patient1 = new patient("Raza Khan", "15/03/1985", "male", "10/11/2023", "", "5", null);
        patient patient2 = new patient("Sara Ahmed", "22/07/1990", "female", "12/11/2023", "", "3", null);
        patient patient3 = new patient("Usman Malik", "30/01/1978", "male", "14/11/2023", "", "7", null);

        // Adding patients to hospital's list
        shifa.add_patient(patient1);
        shifa.add_patient(patient2);
        shifa.add_patient(patient3);

        // adding  patient to doctor's list
        drAli.add_patient(patient1);  // Surgeon treating patient1
        drAhad.add_patient(patient2); // Senior doctor treating patient2
        drFatima.add_patient(patient3); // Intern treating patient3 (under supervision)

        // Displaying cuirrent info
        System.out.println("\n=== Hospital Initial Status ===");
        System.out.println("Hospital: " + shifa.get_name());
        System.out.println("Departments: " + shifa.get_departments().size());
        System.out.println("Patients: " + shifa.get_patients().size());
        System.out.println("ENT Staff: " + ENT.get_staff().size());
        System.out.println("Gynecology Staff: " + Gynae.get_staff().size());

        // caling ovverriden method for each patient
        System.out.println("\n=== Treatment Demonstration ===");
        drAli.treatPatient(patient1);
        drAhad.treatPatient(patient2);
        drFatima.treatPatient(patient3);

        // updating medical_reports using setter mrthod
        patient1.set_medical_report("Cardiac surgery performed successfully");
        patient2.set_medical_report("ENT consultation completed");
        patient3.set_medical_report("Initial examination under supervision");

        // alloting nurse a doctor
        System.out.println("\n=== Nurse Activity ===");
        nurseAmna.assists(drAhad);

        // View medical report of patients
        System.out.println("\n=== Patient Reports ===");
        System.out.println(patient1.get_name() + ": " + patient1.get_medical_report());
        System.out.println(patient2.get_name() + ": " + patient2.get_medical_report());
        System.out.println(patient3.get_name() + ": " + patient3.get_medical_report());

        // Remove a patient
        System.out.println("\n=== Discharging Patient ===");
        shifa.remove_patient(patient2);
        System.out.println("Remaining patients: " + shifa.get_patients().size());

        // Remove a staff member
        System.out.println("\n=== Staff Changes ===");
        ENT.remove_staff(nurseAmna);
        System.out.println("ENT staff count after removal: " + ENT.get_staff().size());

        // hospital status after some changes
        System.out.println("\n=== Final Hospital Status ===");
        System.out.println(" total Patients in hospital: " + shifa.get_patients().size());

    }
}
