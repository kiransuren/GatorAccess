package com.kazza.gatoraccess;

import java.util.ArrayList;

//Class Declaration
public class PeerTutor {

    //Instance variables
    private String tutorName;                   //Name (String)
    private ArrayList<String> tutorCourses;     //Courses (ArrayList String)
    private int tutorGrade;                     //Grade (int)
    private String serialCode;                  //Serial Code (String)
    private String email;                       //Email (string)
    private String phoneNumber;                 //Phone Number (string)
    private String username;                    //Username (string)
    private String password;                    //Password (string)


    //Constructor
    public PeerTutor(String tutorName, ArrayList<String> tutorCourses, int tutorGrade, String serialCode, String email, String phoneNumber, String username, String password) {
        this.tutorName = tutorName;
        this.tutorCourses = tutorCourses;
        this.tutorGrade = tutorGrade;
        this.serialCode = serialCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    //To String override -> returns tutorName
    @Override
    public String toString() {
        return tutorName;
    }


    //Getters and Setters
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
    public String getSerialCode() {
        return serialCode;
    }
    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Formatting Outputs
    public String nameAndCoursesOutput(){
        return tutorName + "\n" + tutorCourses;
    }



}
