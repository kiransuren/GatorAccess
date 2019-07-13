package com.kazza.gatoraccess;

//Java Imports
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
//JavaFX Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class profileController{
    //Side menu bar nodes from FXML
    @FXML
    private Button feedButton;                      //Feed Button
    @FXML
    private Button messageButton;                   //Messages Button
    @FXML
    private Button tutorButton;                     //Tutor Button
    @FXML
    private Button profileButton;                   //Profile Button

    //Tutor Profile Variables (Secondary Center from FXML)
    @FXML
    private Label tutorNameLabel;       //Tutor Name Label
    @FXML
    private Label tutorGradeLabel;      //Tutor Grade Label
    @FXML
    private Label tutorCoursesLabel;    //Tutor Courses Labels
    @FXML
    private Label tutorEmailLabel;      //Tutor Email Label
    @FXML
    private Label tutorPhoneNumberLabel;    //Tutor Phone Number Label


    //Center Layout Nodes from FXML
    @FXML
    private TextField nameField;                    //Name TextField
    @FXML
    private TextField gradeField;                   //Grade TextField
    @FXML
    private TextField emailField;                   //Email TextField
    @FXML
    private TextField pnumberField;                 //Phone Number TextField
    @FXML
    private ComboBox courseComboBox1;               //Course ComboBox 1
    @FXML
    private ComboBox courseComboBox2;               //Course ComboBox 2
    @FXML
    private ComboBox courseComboBox3;               //Course ComboBox 3
    @FXML
    private ComboBox courseComboBox4;               //Course ComboBox 4
    @FXML
    private ComboBox courseComboBox5;               //Course ComboBox 5
    @FXML
    private ComboBox courseComboBox6;               //Course ComboBox 6

    private PeerTutor selfTutor;
    private Scanner scanner;                //Scanner to get information from .txt file
    private String[] tempData;

    //Button Styles (for hovered and idle)
    final String HOVERED_BUTTON_STYLE= "-fx-background-color:#354560";
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #425b84;";



    //Initialization Method
    public void initialize() {
        //Button Dynamics
        feedButton.setOnMouseEntered(e -> feedButton.setStyle(HOVERED_BUTTON_STYLE));
        feedButton.setOnMouseExited(e -> feedButton.setStyle(IDLE_BUTTON_STYLE));
        messageButton.setOnMouseEntered(e -> messageButton.setStyle(HOVERED_BUTTON_STYLE));
        messageButton.setOnMouseExited(e -> messageButton.setStyle(IDLE_BUTTON_STYLE));
        tutorButton.setOnMouseEntered(e -> tutorButton.setStyle(HOVERED_BUTTON_STYLE));
        tutorButton.setOnMouseExited(e -> tutorButton.setStyle(IDLE_BUTTON_STYLE));
        profileButton.setOnMouseEntered(e -> profileButton.setStyle(HOVERED_BUTTON_STYLE));
        profileButton.setOnMouseExited(e -> profileButton.setStyle(IDLE_BUTTON_STYLE));

        try{
            scanner = new Scanner(new BufferedReader(new FileReader("src/resources/currUserFile.txt")));
            while (scanner.hasNextLine()){
                String tempInput = scanner.nextLine();
                tempData = tempInput.split("~");
            }

        }catch (IOException e){                     //Catch IOException
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }

        selfTutor = toPeerTutor(tempData);

        tutorNameLabel.setText(selfTutor.getTutorName());                                                                    //Set name label
        tutorGradeLabel.setText("\nGrade: " + Integer.toString(selfTutor.getTutorGrade()));                                    //Set grade label
        tutorEmailLabel.setText("Email: " + selfTutor.getEmail());                                                           //Set email label
        tutorPhoneNumberLabel.setText("Phone Number: " + selfTutor.getPhoneNumber());                                        //Set phone number label
        tutorCoursesLabel.setText("Courses Taught:\n");                                                                   //Set courses label
        for (int i = 0; i < (selfTutor.getTutorCourses()).size(); i++) {                                                        //Loop through courses
            tutorCoursesLabel.setText(tutorCoursesLabel.getText() + "\n\t-" + selfTutor.getTutorCourses().get(i));          //reformat data and add to label
        }

    }

    //Move to window/tab method
    @FXML
    public void moveToTab(ActionEvent e) throws Exception {

        Stage stage = null;                                                         //Create new stage -> default:null
        Parent root = null;                                                         //Create new parent -> default:null

        //Check button source


        if(e.getSource().equals(feedButton)) {                                      //Check if button source: feedButton
            stage = (Stage) feedButton.getScene().getWindow();                      //Get current scene and window
            root = FXMLLoader.load(getClass().getResource("feedWindow.fxml"));   //Set root to feedWindow.fxml

        } else if(e.getSource().equals(messageButton)) {                                   //Check if button source: messageButton
            stage = (Stage) messageButton.getScene().getWindow();                          //Get current scene and window
            root = FXMLLoader.load(getClass().getResource("messagesWindow.fxml"));      //Set root to messagesWindow.fxml

        }else if(e.getSource().equals(tutorButton)) {                               //Check if button source: tutorButton
            stage = (Stage) tutorButton.getScene().getWindow();                     //Get current scene and window
            root = FXMLLoader.load(getClass().getResource("tutorWindow.fxml"));  //Set root to tutorWindow.fxml

        }else if(e.getSource().equals(profileButton)) {                                 //Check if button source: profileButton
            stage = (Stage) profileButton.getScene().getWindow();                       //Get current scene and window
            root = FXMLLoader.load(getClass().getResource("profileWindow.fxml"));    //Set root to profileWindow.fxml

        }

        //Set scene and show new scene
        Scene scene = new Scene(root, 1050, 700);           //Create new scene with root
        stage.setScene(scene);                                            //Set stage with new scene
        stage.show();                                                     //Show stage
    }



    //Convert from txt file input to PeerTutor Object and return tempTutor
    public PeerTutor toPeerTutor(String[] inputData){
        //Get Data from input data and store in correct var
        String peerName = inputData[0];
        ArrayList <String> courses1 = new ArrayList<String>(Arrays.asList((inputData[1]).split("%")));
        Integer grade = Integer.parseInt(inputData[2]);
        String serialCode = inputData[3];
        String email = inputData[4];
        String phoneNum = inputData[5];
        String username = inputData[6];
        String password = inputData[7];

        //Create temporary tutor object
        PeerTutor tempTutor = new PeerTutor(peerName, courses1, grade, serialCode, email, phoneNum, username, password);
        return tempTutor;
    }




}
