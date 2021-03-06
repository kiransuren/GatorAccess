package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class profileController{
    //Get variables from FXML
    @FXML
    private Button feedButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button tutorButton;
    @FXML
    private Button profileButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField gradeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField pnumberField;
    @FXML
    private ComboBox courseComboBox1;
    @FXML
    private ComboBox courseComboBox2;
    @FXML
    private ComboBox courseComboBox3;
    @FXML
    private ComboBox courseComboBox4;
    @FXML
    private ComboBox courseComboBox5;
    @FXML
    private ComboBox courseComboBox6;



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


    @FXML
    public void createNewAccount(ActionEvent e) throws Exception{
        String name = nameField.getText();
        String grade = gradeField.getText();
        String serial = "1";
        String email = emailField.getText();
        String pnumber = pnumberField.getText();
        Boolean once = true;

        ArrayList <ComboBox> boxList = new ArrayList<>();
        Collections.addAll(boxList,courseComboBox1, courseComboBox2, courseComboBox3, courseComboBox4, courseComboBox5, courseComboBox6);
        String courseStr = "";
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
                System.out.println("Leave");
            }
        }


        try(FileWriter locFile = new FileWriter("src/fakeResources/peerTutorsData.txt")){
            locFile.write(name + "~" + courseStr + "~" + grade + "~" + serial + "~" + email + "~" + pnumber+"\n");
        }

    }


}
