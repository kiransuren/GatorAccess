package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class messagesController {

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
    private VBox mainContact;
    @FXML
    private VBox messageScreen;

    @FXML
    private ListView contactsList;
    @FXML
    private TextField contactTextField;


    //FAKE INFORMATION
    private ArrayList <String> courses1 = new ArrayList<String>(Arrays.asList("Grade 11 Functions", "Grade 10 Math"));
    private ArrayList <String> courses2 = new ArrayList<String>(Arrays.asList("Grade 12 Advanced Functions", "Grade 9 Science"));
    private ArrayList <String> courses3 = new ArrayList<String>(Arrays.asList("Grade 10 Math", "Grade 10 English"));
    private ArrayList <String> courses4 = new ArrayList<String>(Arrays.asList("Grade 11 Chemistry", "Grade 11 Physics"));
    private ArrayList <String> courses5 = new ArrayList<String>(Arrays.asList("Grade 9 Math", "Grade 9 Geography"));

    private List<PeerTutor> tutors;
    private HashMap<Double, PeerTutor> contactMap = new HashMap<Double, PeerTutor>();

    public void initialize() {
        //Create fake tutor data and add to ArrayList
        PeerTutor item1 = new PeerTutor("Edward G.", courses1, 12, 0, "edwardmath@gmail.com", "04234235");
        PeerTutor item2 = new PeerTutor("David T.", courses2, 11, 1, "david.cooldude@gmail.com", "95382038");
        PeerTutor item3 = new PeerTutor("Leena F.", courses3, 11, 2, "leenf@ddsbstudent.ca", "03372947");
        PeerTutor item4 = new PeerTutor("Derek N.", courses4, 12, 3, "chemistryguy@ddsbstudent.ca", "123949235");
        PeerTutor item5 = new PeerTutor("Jill P.", courses5, 10, 4, "freshmantutor@gmail.com", "02758631");
        PeerTutor item6 = new PeerTutor("Paolo S.", courses1, 12, 5, "edwardmath@gmail.com", "04234235");
        PeerTutor item7 = new PeerTutor("Olga V.", courses2, 11, 6, "david.cooldude@gmail.com", "95382038");
        PeerTutor item8 = new PeerTutor("Yun L.", courses3, 11, 7, "leenf@ddsbstudent.ca", "03372947");
        PeerTutor item9 = new PeerTutor("Helena K.", courses4, 12, 8, "chemistryguy@ddsbstudent.ca", "123949235");
        PeerTutor item10 = new PeerTutor("Eliza P.", courses5, 10, 9, "freshmantutor@gmail.com", "02758631");

        tutors = new ArrayList<PeerTutor>();
        Collections.addAll(tutors, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);

        for (int i = 0; i < tutors.size(); i++) {
            PeerTutor tempItem;
            tempItem = tutors.get(i);
            contactsList.getItems().add(tempItem.getTutorName());
            contactMap.put(tempItem.getSerialCode(), tempItem);

        }
        //FAKE INFORMATION

        setNodeVisibility(mainContact, true);
        setNodeVisibility(messageScreen, false);
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
    public void handleClickListView(){
        String item = (String) contactsList.getSelectionModel().getSelectedItem();
        int itemPos = contactsList.getSelectionModel().getSelectedIndex();
        System.out.println(item + " at " + itemPos);
        PeerTutor tutorItem = (PeerTutor) contactMap.get(itemPos);


//        for (int i = 0; i < l.size() ; i++){
//            System.out.println(l.get(i));
//        }

        setNodeVisibility(mainContact, false);
        setNodeVisibility(messageScreen, true);


    }

    public void setNodeVisibility(Node item, Boolean s){
        item.setVisible(s);
        item.setManaged(s);

    }
}
