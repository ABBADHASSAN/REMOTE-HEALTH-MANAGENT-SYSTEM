package com.example.rpms.model;





public class Feedback {
    // datafields
    String doctorName;
    String patientName;
    private String comments;
    private String date;// date of the feedback

    public Feedback(String comments, String date, String doctorName, String patientName) {
        this.comments = comments;
        this.date = date;
        this.doctorName = doctorName;
        this.patientName = patientName;

    }

    // getters and setters
    public String getComments() { return comments; }
    public String getDate() { return date; }
    public String getDoctorName() { return doctorName; }
    public String getPatientName() { return patientName; }


    


    // overriden tostring to display details
    @Override
    public String toString() {
        return  "Feedback{" +
                "doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", comments='" + comments + '\'' +
                ", date='" + date + '\'' +
                '}';

    }
}
