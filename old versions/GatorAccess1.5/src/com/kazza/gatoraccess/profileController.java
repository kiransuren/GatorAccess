package com.kazza.gatoraccess;

//Java Imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
//JavaFX Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    //Button Styles (for hovered and idle)
    final String HOVERED_BUTTON_STYLE= "-fx-background-color:#354560";
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #425b84;";

    public void getProf(PeerTutor n){
        selfTutor = n;
    }

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

        ArrayList<String> clist = new ArrayList<>();                                                          //Create ArrayList with all courses
        Collections.addAll(clist,"Grade 9 Math", "Grade 9 English", "Grade 9 Geography","Grade 9 Science",
                "Grade 10 Math","Grade 10 English","Grade 10 Science","Grade 10 History",
                "Grade 11 Functions","Grade 11 Physics","Grade 11 Chemistry","Grade 11 Biology","Grade 11 English",
                "Grade 12 Advanced Functions","Grade 12 Calculus and Vectors","Grade 12 Physics","Grade 12 Chemistry","Grade 12 Biology","Grade 12 English");

        ArrayList <ComboBox> boxList = new ArrayList<>();       //Create new ArrayList with ComboBox type
        Collections.addAll(boxList,courseComboBox1, courseComboBox2, courseComboBox3, courseComboBox4, courseComboBox5, courseComboBox6);       //Add Course ComboBoxes to ArrayList

        //Add courses to ComboBoxes
        for(int p =0; p < boxList.size(); p++) {                //Iterate through all ComboBoxes
            ComboBox tempBox = boxList.get(p);
            for(int i =0; i <clist.size(); i++){                //Iterate through all courses in Clist
                tempBox.getItems().add(clist.get(i));           //Add to temp ComboBox
            }
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



    //Create a new account from entries and add to .txt file
    @FXML
    public void createNewAccount(ActionEvent e) throws Exception{
        String name = nameField.getText();                      //Get nameField text
        String grade = gradeField.getText();                    //Get gradeField text
        String serial = "1";                                    //Create random serial code
        String email = emailField.getText();                    //Get emailField text
        String pnumber = pnumberField.getText();                //Get pnumberField text
        Boolean once = true;

        ArrayList <ComboBox> boxList = new ArrayList<>();       //Create new ArrayList with ComboBox type
        Collections.addAll(boxList,courseComboBox1, courseComboBox2, courseComboBox3, courseComboBox4, courseComboBox5, courseComboBox6);       //Add Course ComboBoxes to ArrayList

        String courseStr = "";                                  //Initialize courseStr (to be written to file)
        for (int i = 0; i <boxList.size(); i++){
            try{
                String tempC = boxList.get(i).getValue().toString();
                if (tempC != null){
                    if (once) {
                        courseStr = courseStr + tempC;
                        once = false;
                    }else{
                        courseStr = courseStr + "%" + tempC;
                    }
                }
                System.out.println(tempC);
            } catch (NullPointerException error){
                System.out.println("No Input");
            }
        }


        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("src/resources/peerTutorsData.txt", true))){             //Write data to file
            locFile.write(name + "~" + courseStr + "~" + grade + "~" + serial + "~" + email + "~" + pnumber+"\n");
        }

    }


}
