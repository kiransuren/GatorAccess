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


    private ArrayList <String> courses1 = new ArrayList<String>(Arrays.asList("Grade 11 Functions", "Grade 10 Math"));
    private ArrayList <String> courses2 = new ArrayList<String>(Arrays.asList("Grade 12 Advanced Functions", "Grade 9 Science"));
    private ArrayList <String> courses3 = new ArrayList<String>(Arrays.asList("Grade 10 Math", "Grade 10 English"));
    private ArrayList <String> courses4 = new ArrayList<String>(Arrays.asList("Grade 11 Chemistry", "Grade 11 Physics"));
    private ArrayList <String> courses5 = new ArrayList<String>(Arrays.asList("Grade 9 Math", "Grade 9 Geography"));

    private  List<PeerTutor> tutors;

    public void initialize(){
        //Create fake tutor data and add to ArrayList
        PeerTutor item1 = new PeerTutor("Edward G.", courses1, 12, 1, "edwardmath@gmail.com", "04234235");
        PeerTutor item2 = new PeerTutor("David T.", courses2, 11, 2, "david.cooldude@gmail.com", "95382038");
        PeerTutor item3 = new PeerTutor("Leena F.", courses3, 11, 3, "leenf@ddsbstudent.ca", "03372947");
        PeerTutor item4 = new PeerTutor("Derek N.", courses4, 12, 4, "chemistryguy@ddsbstudent.ca", "123949235");
        PeerTutor item5 = new PeerTutor("Jill P.", courses5, 10, 5, "freshmantutor@gmail.com", "02758631");

        tutors = new ArrayList<PeerTutor>();
        Collections.addAll(tutors, item1, item2, item3, item4, item5);

        for(int i = 0; i < tutors.size(); i++){
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            tutorListView.getItems().add(tempItem.getTutorName() + "\n" + tempItem.getTutorCourses());

        }

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

    public void setNodeVisibility(Node item, Boolean s){
        item.setVisible(s);
        item.setManaged(s);

    }
}
