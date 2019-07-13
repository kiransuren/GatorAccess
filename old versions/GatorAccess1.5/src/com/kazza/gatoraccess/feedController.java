package com.kazza.gatoraccess;

//JavaFX Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//Main Class
public class feedController {

    //Side Bar Buttons from FXML
    @FXML
    private Button feedButton;          //Feed Button
    @FXML
    private Button messageButton;       //Messages Button
    @FXML
    private Button tutorButton;         //Tutor Button
    @FXML
    private Button profileButton;       //Profile Button

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


}
