package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class tutorController {

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
    private Button tutorSearchButton;
    @FXML
    private TextField tutorTextField;
    @FXML
    private ListView tutorListView;
    @FXML
    private Label errorLabel;
    @FXML
    private VBox listViewVBox;
    @FXML
    private HBox topSearchBarHBox;
    @FXML
    private VBox tutorVBox;

    //Tutor Profile Variables
    @FXML
    private Label tutorNameLabel;
    @FXML
    private Label tutorGradeLabel;
    @FXML
    private Label tutorCoursesLabel;
    @FXML
    private Label tutorEmailLabel;
    @FXML
    private Label tutorPhoneNumberLabel;


    private  List<PeerTutor> tutors;
    private Scanner scanner;

    public void initialize(){
        //Read Fake data
        tutors = new ArrayList<PeerTutor>();
        try{
            scanner = new Scanner(new BufferedReader(new FileReader("src/fakeResources/peerTutorsData.txt")));
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

        //Loop through tutors and add to ListView
        for(int i = 0; i < tutors.size(); i++){
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            tutorListView.getItems().add(tempItem);

        }
        //FAKE INFORMATION END

        setNodeVisibility(errorLabel, false);
        setNodeVisibility(tutorListView, false);
        setNodeVisibility(listViewVBox, true);
        setNodeVisibility(topSearchBarHBox,true);
        setNodeVisibility(tutorVBox, false);
    }


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
    public void onTutorSearchClicked(){
        //Check if item was added
        Boolean itemNotAdded = true;

        //Set Visibilites
        setNodeVisibility(tutorListView, true);
        setNodeVisibility(errorLabel, false);

        //Get User search
        String userSearch;
        userSearch = ((tutorTextField.getText()).toLowerCase()).replaceAll("\\s+", "");
        System.out.println(userSearch);

        //Check all tutors for userSearch
        tutorListView.getItems().clear();
        for(int i = 0; i < tutors.size(); i++){     //Loop through tutor List
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            for(int n = 0; n < tempItem.getTutorCourses().size(); n++){     //Loop through tutorCourses for each tutor
                if (((tempItem.getTutorCourses().get(n).toLowerCase()).replaceAll("\\s+", "")).contains(userSearch.toLowerCase())){ //Check if userSearch is in tutor course
                    tutorListView.getItems().add(tempItem);          //Add ListView
                    itemNotAdded= false;
                    break;
                }
            }

        }

        System.out.println(itemNotAdded);

        if (itemNotAdded){
            setNodeVisibility(tutorListView, false);
            setNodeVisibility(errorLabel, true);

        }

    }

    @FXML
    public void handleClickListView(){
        PeerTutor item = (PeerTutor) tutorListView.getSelectionModel().getSelectedItem();
        System.out.println(item.getTutorName());
        setNodeVisibility(topSearchBarHBox,false);
        setNodeVisibility(listViewVBox, false);
        setNodeVisibility(tutorVBox, true);
        tutorNameLabel.setText(item.getTutorName());
        tutorGradeLabel.setText("Grade: " + Integer.toString(item.getTutorGrade()));
        tutorEmailLabel.setText("Email: " + item.getEmail());
        tutorPhoneNumberLabel.setText("Phone Number: " + item.getPhoneNumber());
        tutorCoursesLabel.setText("Courses Taught:");
        for (int i=0; i < (item.getTutorCourses()).size(); i++){
            tutorCoursesLabel.setText(tutorCoursesLabel.getText() + "\n\t- " + item.getTutorCourses().get(i));
        }





    }

    //Set visibility and interaction of any node
    public void setNodeVisibility(Node item, Boolean s){
        item.setVisible(s);
        item.setManaged(s);

    }

    //Convert from txt file input to PeerTutor Object and return tempTutor
    public PeerTutor toPeerTutor(String[] inputData){
        //Get Data from input data and store in correct var
        String peerName = inputData[0];
        ArrayList <String> courses1 = new ArrayList<String>(Arrays.asList((inputData[1]).split("%")));
        Integer grade = Integer.parseInt(inputData[2]);
        Integer serialCode = Integer.parseInt(inputData[3]);
        String email = inputData[4];
        String phoneNum = inputData[5];

        //Create temporary tutor object
        PeerTutor tempTutor = new PeerTutor(peerName, courses1, grade, serialCode, email, phoneNum);
        return tempTutor;


    }
}
