package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class feedController {

    //Get variables from FXML
    @FXML
    private Button feedButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button tutorButton;
    @FXML
    private Button profileButton;


    //Move to selected tab method

    @FXML
    public void moveToTab(ActionEvent e) throws Exception {

        Stage stage = null;
        Parent root = null;

        //When feed button is selected go to feed window
        if(e.getSource().equals(feedButton)) {
            System.out.println("To Feed Window");
            stage = (Stage) feedButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("feedWindow.fxml"));

            //When message button is selected go to message window
        } else if(e.getSource().equals(messageButton)) {
            System.out.println("To Message Window");
            stage = (Stage) messageButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("messagesWindow.fxml"));

            //When tutor button is selected go to tutor window
        }else if(e.getSource().equals(tutorButton)) {
            System.out.println("To TutorSearch Window");
            stage = (Stage) tutorButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("tutorWindow.fxml"));

            //When profile button is selected go to profile window
        }else if(e.getSource().equals(profileButton)) {
            System.out.println("To Profile Window");
            stage = (Stage) profileButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("profileWindow.fxml"));

        }

        //Set scene and show new scene
        Scene scene = new Scene(root, 1050, 700);
        stage.setScene(scene);
        stage.show();
    }
}
