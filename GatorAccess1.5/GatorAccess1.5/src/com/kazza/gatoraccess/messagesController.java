package com.kazza.gatoraccess;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//Java Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//Main Class
public class messagesController {

    //Side Bar Buttons from FXML
    @FXML
    private Button feedButton;              //Feed Button
    @FXML
    private Button messageButton;           //Message Button
    @FXML
    private Button tutorButton;             //Tutor Button
    @FXML
    private Button profileButton;           //Profile Button

    //Main Screen Nodes from FXML
    @FXML
    private VBox mainContact;               //Primary VBox display (Search for contacts & ListView of contacts)
    @FXML
    private ListView contactsList;          //Contacts ListView for mainContact VBox
    @FXML
    private VBox messageScreen;             //Message display VBox
    @FXML
    private Label errorLabel;
    @FXML
    private TextField contactTextField;

    //Messaging Screen Nodes from FXML
    @FXML
    private Label nameBanner;


    //Misc Variables
    private Scanner scanner;                //Scanner to get information from .txt file
    private  ArrayList<PeerTutor> tutors = new ArrayList<>();        //List of tutors for ListView
    private HashMap<Integer, PeerTutor> contMap = new HashMap<Integer, PeerTutor>();

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
                scanner.close();                    //Close scanner
            }
        }

        Integer n = 0;
        //Loop through data and add to ListView
        for (int i = 0; i< tutors.size(); i++){
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            contactsList.getItems().add(tempItem);
            contMap.put(n, tempItem);
            n++;

        }

        //FAKE INFORMATION

        setNodeVisibility(mainContact, true);
        setNodeVisibility(messageScreen, false);
        setNodeVisibility(errorLabel, false);
    }
    //FINISH FAKE



    //Change a node's visibility and functionality state
    public void setNodeVisibility(Node item, Boolean s){
        item.setVisible(s);             //Set item to visible state to 's'
        item.setManaged(s);             //Set item to managed state to 's'

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


    //Handle when user clicks on a item in the listView
    @FXML
    public void handleClickListView(){

        if(contactsList.getSelectionModel().getSelectedItem() != null) {
            int indexOfItem = contactsList.getSelectionModel().getSelectedIndex();
            PeerTutor item = (PeerTutor) contMap.get(indexOfItem);
            //PeerTutor item = (PeerTutor) contactsList.getSelectionModel().getSelectedItem();       //Get selected item from contactsList ListView and cast as a string
            System.out.println(item);
            setNodeVisibility(mainContact, false);
            setNodeVisibility(messageScreen, true);
            nameBanner.setText(item.getTutorName());
        }

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

    //When contactSearch is clicked
    @FXML
    public void onContactSearchClicked(){
        //Check if item was added
        //Check if item was added
        Boolean itemNotAdded = true;
        int f = 0;

        //Set Visibilities
        setNodeVisibility(contactsList, true);
        setNodeVisibility(errorLabel, false);

        //Get User search
        String userSearch;
        userSearch = ((contactTextField.getText()).toLowerCase()).replaceAll("\\s+", ""); //Get text from field and reformats
        System.out.println(userSearch);

        //Check all tutors for userSearch
        contactsList.getItems().clear();           //Clear items in ListView
        for(int i = 0; i < tutors.size(); i++){     //Loop through tutor List
            PeerTutor tempItem;                     //Temporary PeerTutor Object
            tempItem = tutors.get(i);
            System.out.println(userSearch.toLowerCase());
            if (((tempItem.getTutorName().toLowerCase())).contains(userSearch.toLowerCase())){ //Check if userSearch is in tutor course
                contactsList.getItems().add(tempItem.getTutorName());
                contMap.put(f, tempItem);
                f++;
                itemNotAdded= false;
            }
        }

        if (itemNotAdded){                                  //If no items were added or found
            setNodeVisibility(contactsList, false);     //Set ListView visibility to false
            setNodeVisibility(errorLabel, true);         //Set errorLabel visibility to true

        }

    }


}
