package sample;

import java.util.ArrayList;

public class PeerTutor {
    private String tutorName;
    private ArrayList<String> tutorCourses;
    private int tutorGrade;
    private double serialCode;
    private String email;
    private String phoneNumber;

    public PeerTutor(String tutorName, ArrayList<String> tutorCourses, int tutorGrade, double serialCode, String email, String phoneNumber) {
        this.tutorName = tutorName;
        this.tutorCourses = tutorCourses;
        this.tutorGrade = tutorGrade;
        this.serialCode = serialCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public ArrayList<String> getTutorCourses() {
        return tutorCourses;
    }

    public void setTutorCourses(ArrayList<String> tutorCourses) {
        this.tutorCourses = tutorCourses;
    }

    public int getTutorGrade() {
        return tutorGrade;
    }

    public void setTutorGrade(int tutorGrade) {
        this.tutorGrade = tutorGrade;
    }

    public double getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(double serialCode) {
        this.serialCode = serialCode;
    }

    @Override
    public String toString() {
        return tutorName + "\n" + tutorCourses;
    }
}
