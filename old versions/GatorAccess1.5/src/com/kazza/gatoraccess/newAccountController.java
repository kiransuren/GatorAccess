package com.kazza.gatoraccess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class newAccountController {
    @FXML
    private HBox firstSlide;
    @FXML
    private VBox secondSlide;
    @FXML
    private HBox firstSlideBottom;
    @FXML
    private HBox secondSlideBottom;

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
    private TextField usernameField;                 //Username TextField
    @FXML
    private TextField passwordField;                 //Password TextField

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

    @FXML
    private Button newAccountButton;
    @FXML
    private Label errorLabel;

    private String newName;
    private String newGrade;
    private String newEmail;
    private String newPhoneNumber;
    private String newUsername;
    private String newPassword;
    private ArrayList<String> newCourseList = new ArrayList();

    //Misc Variables
    private List<PeerTutor> tutors;        //List of tutors for ListView
    private Scanner scanner;                //Scanner to get information from .txt file

    //Change a node's visibility and functionality state
    public void setNodeVisibility(Node item, Boolean s){
        item.setVisible(s);             //Set item to visible state to 's'
        item.setManaged(s);             //Set item to managed state to 's'

    }

    public void initialize(){
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


        tutors = new ArrayList<PeerTutor>();
        try{
            scanner = new Scanner(new BufferedReader(new FileReader("src/resources/peerTutorsData.txt")));
            while (scanner.hasNextLine()){
                String tempInput = scanner.nextLine();
                String[] tempData = tempInput.split("~");
                Collections.addAll(tutors, toPeerTutor(tempData));
            }

        }catch (IOException e){                     //Catch IOException
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }

        setNodeVisibility(firstSlide, true);
        setNodeVisibility(firstSlideBottom, true);
        setNodeVisibility(secondSlide, false);
        setNodeVisibility(secondSlideBottom, false);
    }

    @FXML
    public void onNextButtonClicked(ActionEvent e){
        boolean pass1 = false;
        boolean pass2 = false;
        boolean pass3 = false;
        boolean pass4 = false;
        boolean pass5 = true;
        boolean pass6 = true;
        int tempGrade;

        newName = nameField.getText();
        newGrade = gradeField.getText();
        newEmail = emailField.getText();
        newPhoneNumber = pnumberField.getText();
        newUsername = usernameField.getText();
        newPassword = passwordField.getText();

        //Test if valid email
        if(newEmail.contains("@")){
            pass4 = true;
        }else{
            errorLabel.setText("Please enter a valid email into the Email Address Field");
        }

        //Test if phone number is a valid number
        try{
            Integer.parseInt(newPhoneNumber);
            pass3 = true;
        }catch (NumberFormatException error1){
            errorLabel.setText("Please enter a valid number into the Phone Number Field");
        }

        //Test if grade is integer and acceptable
        try{
            tempGrade = Integer.parseInt(newGrade);
            if (!((8 < tempGrade) && (tempGrade < 14))){
                errorLabel.setText("Please enter a valid number into the Grade field");
            }else{
                pass2 = true;
            }
        }catch (NumberFormatException error1){
            errorLabel.setText("Please enter a valid number into the Grade Field");
        }

        //Test if empty fields
        if(newName.equals("") || newGrade.equals("") ||  newEmail.equals("") ||  newPhoneNumber.equals("") ||  newUsername.equals("") ||  newPassword.equals("")){
            errorLabel.setText("Please fill out all fields");
        }else{
            pass1 = true;
        }

        //Test if existing account email or username
        for(int i = 0; i < tutors.size(); i++){
            PeerTutor tempTut = (PeerTutor) tutors.get(i);
            if((tempTut.getEmail()).equals(newEmail)){
                errorLabel.setText("This email has already been used with an account");
                pass5 = false;
            }
            if((tempTut.getUsername()).equals(newUsername)){
                errorLabel.setText("Sorry, this username has already been taken!");
                pass6 = false;
            }
        }

        if(pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
            setNodeVisibility(firstSlide, false);
            setNodeVisibility(firstSlideBottom, false);
            setNodeVisibility(secondSlide, true);
            setNodeVisibility(secondSlideBottom, true);
        }

    }


    //Create a new account from entries and add to .txt file
    @FXML
    public void createNewAccount(ActionEvent e) throws Exception{
        String serial = "1";                                    //Create random serial code
        Boolean once = true;

        ArrayList <ComboBox> boxList = new ArrayList<>();       //Create new ArrayList with ComboBox type
        Collections.addAll(boxList,courseComboBox1, courseComboBox2, courseComboBox3, courseComboBox4, courseComboBox5, courseComboBox6);       //Add Course ComboBoxes to ArrayList

        String courseStr = "";                                  //Initialize courseStr (to be written to file)
        for (int i = 0; i <boxList.size(); i++){
            try{
                String tempC = boxList.get(i).getValue().toString();
                if(!tempC.equals("--Select a course--")) {
                    if (tempC != null) {
                        if (once) {
                            courseStr = courseStr + tempC;
                            once = false;
                        } else {
                            courseStr = courseStr + "%" + tempC;
                        }
                    }
                }
                System.out.println(tempC);
            } catch (NullPointerException error){
                System.out.println("No Input");
            }
        }


        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("src/resources/peerTutorsData.txt", true))){             //Write data to file
            locFile.write(newName + "~" + courseStr + "~" + newGrade + "~" + serial + "~" + newEmail + "~" + newPhoneNumber + "~" + newUsername + "~" + newPassword + "\n" );
        }


        Stage stage = null;                                                         //Create new stage -> default:null
        Parent root = null;                                                         //Create new parent -> default:null

        stage = (Stage) newAccountButton.getScene().getWindow();                          //Get current scene and window
        root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));      //Set root to messagesWindow.fxml
        //Set scene and show new scene
        Scene scene = new Scene(root, 500, 450);           //Create new scene with root
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
