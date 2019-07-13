package com.kazza.gatoraccess;

//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

//JavaFX Imports
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class tutorController {

    //Side Bar Buttons from FXML
    @FXML
    private Button feedButton;          //Feed Button
    @FXML
    private Button messageButton;       //Messages Button
    @FXML
    private Button tutorButton;         //Tutor Button
    @FXML
    private Button profileButton;       //Profile Button

    //Primary Center Nodes from FXML
    @FXML
    private VBox listViewVBox;          //ListView VBox
    @FXML
    private HBox topSearchBarHBox;      //SeachBar HBox
    @FXML
    private VBox tutorVBox;             //Tutor Profile Page VBox

    @FXML
    private TextField tutorTextField;   //Tutor TextField
    @FXML
    private ListView tutorListView;     //Tutor ListView
    @FXML
    private Label errorLabel;           //Error Label (after no results from search)


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

    //Misc Variables
    private  List<PeerTutor> tutors;        //List of tutors for ListView
    private Scanner scanner;                //Scanner to get information from .txt file
    private HashMap<Integer, PeerTutor> tutMap = new HashMap<Integer, PeerTutor>();

    //Button Styles (for hovered and idle)
    final String HOVERED_BUTTON_STYLE= "-fx-background-color:#354560";
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #425b84;";

    //Initialization Method
    public void initialize(){
        //Button Dynamics
        feedButton.setOnMouseEntered( e-> feedButton.setStyle(HOVERED_BUTTON_STYLE));
        feedButton.setOnMouseExited( e-> feedButton.setStyle(IDLE_BUTTON_STYLE));
        messageButton.setOnMouseEntered( e-> messageButton.setStyle(HOVERED_BUTTON_STYLE));
        messageButton.setOnMouseExited( e-> messageButton.setStyle(IDLE_BUTTON_STYLE));
        tutorButton.setOnMouseEntered( e-> tutorButton.setStyle(HOVERED_BUTTON_STYLE));
        tutorButton.setOnMouseExited( e-> tutorButton.setStyle(IDLE_BUTTON_STYLE));
        profileButton.setOnMouseEntered( e-> profileButton.setStyle(HOVERED_BUTTON_STYLE));
        profileButton.setOnMouseExited( e-> profileButton.setStyle(IDLE_BUTTON_STYLE));

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

        for (int i = 0; i < tutors.size(); i++) {
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            tutorListView.getItems().add(tempItem.nameAndCoursesOutput());
            //tutMap.put(tempItem.getSerialCode(), tempItem);

        }

        //FAKE INFORMATION END

        setNodeVisibility(errorLabel, false);
        setNodeVisibility(tutorListView, false);
        setNodeVisibility(listViewVBox, true);
        setNodeVisibility(topSearchBarHBox,true);
        setNodeVisibility(tutorVBox, false);
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

    @FXML
    public void onTutorSearchClicked(){
        //Check if item was added
        //Check if item was added
        Boolean itemNotAdded = true;
        int f = 0;

        //Set Visibilities
        setNodeVisibility(tutorListView, true);
        setNodeVisibility(errorLabel, false);

        //Get User search
        String userSearch;
        userSearch = ((tutorTextField.getText()).toLowerCase()).replaceAll("\\s+", ""); //Get text from field and reformats
        System.out.println(userSearch);

        //Check all tutors for userSearch
        tutorListView.getItems().clear();           //Clear items in ListView
        for(int i = 0; i < tutors.size(); i++){     //Loop through tutor List
            PeerTutor tempItem;                     //Temporary PeerTutor Object
            tempItem = tutors.get(i);
            for(int n = 0; n < tempItem.getTutorCourses().size(); n++){     //Loop through tutorCourses for each tutor
                if (((tempItem.getTutorCourses().get(n).toLowerCase()).replaceAll("\\s+", "")).contains(userSearch.toLowerCase())){ //Check if userSearch is in tutor course
                    tutorListView.getItems().add(tempItem.nameAndCoursesOutput());
                    tutMap.put(f, tempItem);
                    f++;
                    itemNotAdded= false;
                    break;
                }
            }

        }

        if (itemNotAdded){                                  //If no items were added or found
            setNodeVisibility(tutorListView, false);     //Set ListView visibility to false
            setNodeVisibility(errorLabel, true);         //Set errorLabel visibility to true

        }

    }

    @FXML
    public void handleClickListView(){

//        Object tutorItemObject = tutorListView.getSelectionModel().getSelectedItem();
//        String[] SelectedItem = tutorItemObject.toString().split("\n");S
//        PeerTutor tutorItem = (PeerTutor) tutMap.get(SelectedItem[2]);
        int b = 0;
        if(tutorListView.getSelectionModel().getSelectedItem() != null) {
            System.out.println(tutorListView.getSelectionModel().getSelectedItem());
            int indexOfItem = tutorListView.getSelectionModel().getSelectedIndex();
            PeerTutor tutorItem = (PeerTutor) tutMap.get(indexOfItem);
            System.out.println(tutorItem);
            setNodeVisibility(topSearchBarHBox, false);                                                                   //Hide search bar
            setNodeVisibility(listViewVBox, false);                                                                      //Hide listView
            setNodeVisibility(tutorVBox, true);                                                                          //Show tutor profile VBox
            tutorNameLabel.setText(tutorItem.getTutorName());                                                                    //Set name label
            tutorGradeLabel.setText("\nGrade: " + Integer.toString(tutorItem.getTutorGrade()));                                    //Set grade label
            tutorEmailLabel.setText("Email: " + tutorItem.getEmail());                                                           //Set email label
            tutorPhoneNumberLabel.setText("Phone Number: " + tutorItem.getPhoneNumber());                                        //Set phone number label
            tutorCoursesLabel.setText("Courses Taught:\n");                                                                   //Set courses label
            for (int i = 0; i < (tutorItem.getTutorCourses()).size(); i++) {                                                        //Loop through courses
                tutorCoursesLabel.setText(tutorCoursesLabel.getText() + "\n\t-" + tutorItem.getTutorCourses().get(i));          //reformat data and add to label
            }
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
