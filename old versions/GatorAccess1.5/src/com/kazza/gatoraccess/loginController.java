package com.kazza.gatoraccess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class loginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    //Misc Variables
    private  List<PeerTutor> tutors;        //List of tutors for ListView
    private Scanner scanner;                //Scanner to get information from .txt file


    public void initialize(){
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
    }



    @FXML
    public void onLoginButtonClicked (ActionEvent e) throws Exception{
        Boolean passed = null;
        String usernameInput = null;
        String passwordInput = null;
        Stage stage = null;                                                         //Create new stage -> default:null
        Parent root = null;                                                         //Create new parent -> default:null

        try {
            usernameInput = usernameField.getText();
            passwordInput = passwordField.getText();
            passed = true;
        } catch (NullPointerException n){
            System.out.println("null input");
            errorLabel.setText("Sorry, invalid input! Please try again");
            passed = false;
        }

        System.out.println(usernameInput + "\n" + passwordInput + "\n" + passed);

        if(passed){

            for(int i=0; i < tutors.size(); i++){
                PeerTutor tempTutor  = tutors.get(i);
                if((usernameInput.equals(tempTutor.getUsername())) && (passwordInput.equals(tempTutor.getPassword()))){
                    stage = (Stage) loginButton.getScene().getWindow();                          //Get current scene and window
                    root = FXMLLoader.load(getClass().getResource("feedWindow.fxml"));      //Set root to messagesWindow.fxml
                    //Set scene and show new scene
                    Scene scene = new Scene(root, 1050, 700);           //Create new scene with root
                    stage.setScene(scene);                                            //Set stage with new scene
                    stage.show();                                                     //Show stage
                }
            }

            errorLabel.setText("Sorry, invalid input! Please try again");
        }



    }

    @FXML
    public void onSignUpButtonClicked (ActionEvent e) throws Exception{
        Stage stage = null;                                                         //Create new stage -> default:null
        Parent root = null;                                                         //Create new parent -> default:null

        stage = (Stage) signUpButton.getScene().getWindow();                          //Get current scene and window
        root = FXMLLoader.load(getClass().getResource("newAccountWindow.fxml"));      //Set root to messagesWindow.fxml
        //Set scene and show new scene
        Scene scene = new Scene(root, 700, 550);           //Create new scene with root
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
