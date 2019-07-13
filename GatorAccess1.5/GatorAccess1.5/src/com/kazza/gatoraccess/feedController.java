package com.kazza.gatoraccess;

//JavaFX Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;

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

    //Main Window FXML Variables
    @FXML
    private VBox centralWindow;
    @FXML
    private ScrollPane venuja;
    @FXML
    private VBox primaryContentBox;
    @FXML
    private VBox newPostVBOX;

    //Post Blocks
    @FXML
    private VBox post1Main;
    @FXML
    private HBox post1Secondary;
    @FXML
    private VBox post2Main;
    @FXML
    private HBox post2Secondary;
    @FXML
    private VBox post3Main;
    @FXML
    private HBox post3Secondary;
    @FXML
    private VBox post4Main;
    @FXML
    private HBox post4Secondary;
    @FXML
    private VBox post5Main;
    @FXML
    private HBox post5Secondary;
    @FXML
    private VBox post6Main;
    @FXML
    private HBox post6Secondary;
    @FXML
    private VBox post7Main;
    @FXML
    private HBox post7Secondary;
    @FXML
    private VBox post8Main;
    @FXML
    private HBox post8Secondary;
    @FXML
    private VBox post9Main;
    @FXML
    private HBox post9Secondary;
    @FXML
    private VBox post10Main;
    @FXML
    private HBox post10Secondary;

    @FXML
    private TextArea postTextArea;
    @FXML
    private Label errorLabel;


    //Misc Variables
    ArrayList<PostBlock> posts = new ArrayList<>();
    private Scanner scanner;                //Scanner to get information from .txt file for posts
    private Scanner scannerB;                //Scanner to get information from .txt file for selfTutor
    private PeerTutor selfTutor;
    private String[] tempData;

    // display time and date using toString()
    SimpleDateFormat dateFT = new SimpleDateFormat ("dd/MM/yyyy");


    //Button Styles (for hovered and idle)
    final String HOVERED_BUTTON_STYLE= "-fx-background-color:#354560";
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #425b84;";

    //Initialization Method
    public void initialize() {

        //Hide all nodes as a default
        setNodeVisibilitySingle(primaryContentBox, true);
        setNodeVisibilitySingle(newPostVBOX, false);
        setNodeVisibility(post1Main, post1Secondary, false);
        setNodeVisibility(post2Main, post2Secondary, false);
        setNodeVisibility(post3Main, post3Secondary, false);
        setNodeVisibility(post4Main, post4Secondary, false);
        setNodeVisibility(post5Main, post5Secondary, false);
        setNodeVisibility(post6Main, post6Secondary, false);
        setNodeVisibility(post7Main, post7Secondary, false);
        setNodeVisibility(post8Main, post8Secondary, false);
        setNodeVisibility(post9Main, post9Secondary, false);
        setNodeVisibility(post10Main, post10Secondary, false);


        //Button Dynamics
        feedButton.setOnMouseEntered(e -> feedButton.setStyle(HOVERED_BUTTON_STYLE));
        feedButton.setOnMouseExited(e -> feedButton.setStyle(IDLE_BUTTON_STYLE));
        messageButton.setOnMouseEntered(e -> messageButton.setStyle(HOVERED_BUTTON_STYLE));
        messageButton.setOnMouseExited(e -> messageButton.setStyle(IDLE_BUTTON_STYLE));
        tutorButton.setOnMouseEntered(e -> tutorButton.setStyle(HOVERED_BUTTON_STYLE));
        tutorButton.setOnMouseExited(e -> tutorButton.setStyle(IDLE_BUTTON_STYLE));
        profileButton.setOnMouseEntered(e -> profileButton.setStyle(HOVERED_BUTTON_STYLE));
        profileButton.setOnMouseExited(e -> profileButton.setStyle(IDLE_BUTTON_STYLE));

        //Get Post Data
        try{
            scanner = new Scanner(new BufferedReader(new FileReader("src/resources/postData.txt")));
            while (scanner.hasNextLine()){
                String tempInput = scanner.nextLine();
                String[] tempData = tempInput.split("~%~");
                Collections.addAll(posts, toPostBlock(tempData));
            }

        }catch (IOException e){                     //Catch IOException
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }

        //Initialize Content
        for (int i=0; i<posts.size(); i++){
            //ArrayList<PostBlock> sortedPostsArray = new ArrayList<>();
            //sortedPostsArray = postDateSort(posts);
            //PostBlock tempPost = sortedPostsArray.get(i);
            PostBlock tempPost = posts.get(i);
            String tempName = tempPost.getCreator();
            String tempDate = tempPost.getDate();
            String tempContent = tempPost.getTextContent();
            Label labelContent = new Label(tempContent + "\n\n");
            labelContent.setWrapText(true);
            System.out.println(i);
            switch (i){
                case 0:
                    post1Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post1Main.getChildren().add(labelContent);
                    setNodeVisibility(post1Main, post1Secondary, true);
                    break;
                case 1:
                    post2Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post2Main.getChildren().add(labelContent);
                    setNodeVisibility(post2Main, post2Secondary, true);
                    break;
                case 2:
                    post3Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post3Main.getChildren().add(labelContent);
                    setNodeVisibility(post3Main, post3Secondary, true);
                    break;
                case 3:
                    post4Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post4Main.getChildren().add(labelContent);
                    setNodeVisibility(post4Main, post4Secondary, true);
                    break;
                case 4:
                    post5Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post5Main.getChildren().add(labelContent);
                    setNodeVisibility(post5Main, post5Secondary, true);
                    break;
                case 5:
                    post6Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post6Main.getChildren().add(labelContent);
                    setNodeVisibility(post6Main, post6Secondary, true);
                    break;
                case 6:
                    post7Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post7Main.getChildren().add(labelContent);
                    setNodeVisibility(post7Main, post7Secondary, true);
                    break;
                case 7:
                    post8Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post8Main.getChildren().add(labelContent);
                    setNodeVisibility(post8Main, post8Secondary, true);
                    break;
                case 8:
                    post9Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post9Main.getChildren().add(labelContent);
                    setNodeVisibility(post9Main, post9Secondary, true);
                    break;
                case 9:
                    post10Secondary.getChildren().addAll(new Label(tempName), new Label(tempDate));
                    post10Main.getChildren().add(labelContent);
                    setNodeVisibility(post10Main, post10Secondary, true);
                    break;
                default:
                    post1Main.getChildren().add(new Label("Sorry, no posts were found!!!"));
            }
        }

        venuja.setFitToHeight(false);
        venuja.setFitToWidth(true);


        try{
            scannerB = new Scanner(new BufferedReader(new FileReader("src/resources/currUserFile.txt")));
            while (scannerB.hasNextLine()){
                String tempInput = scannerB.nextLine();
                tempData = tempInput.split("~");
            }

        }catch (IOException e){                     //Catch IOException
            e.printStackTrace();
        }finally {
            if(scannerB != null){
                scannerB.close();
            }
        }

        selfTutor = toPeerTutor(tempData);
    }


    //Change a node's visibility and functionality state
    public void setNodeVisibility(Node item, Node item2, Boolean s){
        item.setVisible(s);             //Set item to visible state to 's'
        item.setManaged(s);             //Set item to managed state to 's'

        item2.setVisible(s);             //Set item to visible state to 's'
        item2.setManaged(s);             //Set item to managed state to 's'

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
    public void onCreatePostClicked(ActionEvent e){
        setNodeVisibilitySingle(centralWindow, false);
        setNodeVisibilitySingle(newPostVBOX, true);

    }

    @FXML
    public void onPostButtonClicked(ActionEvent e) throws Exception{
        Stage stage = null;                                                         //Create new stage -> default:null
        Parent root = null;                                                         //Create new parent -> default:null



        // Instantiate a Date object
        Date dateObject = new Date();

        String name = selfTutor.getTutorName();
        String dateString = dateFT.format(dateObject);
        String userInput = postTextArea.getText();

        if (userInput.length() >  150){
            errorLabel.setText("Sorry, this post is too long!");
        }else{
            try (BufferedWriter locFile = new BufferedWriter(new FileWriter("src/resources/postData.txt", true))) {             //Write data to file
                locFile.write(name + "~%~" + dateString + "~%~" + userInput + "~%~" + "121" + "\n");
            }
        }

        //Reset and Refresh Page
        stage = (Stage) feedButton.getScene().getWindow();                      //Get current scene and window
        root = FXMLLoader.load(getClass().getResource("feedWindow.fxml"));   //Set root to feedWindow.fxml

        //Set scene and show new scene
        Scene scene = new Scene(root, 1050, 700);           //Create new scene with root
        stage.setScene(scene);                                            //Set stage with new scene
        stage.show();                                                     //Show stage
    }

    //Set visibility and interaction of any node
    public void setNodeVisibilitySingle(Node item, Boolean s){
        item.setVisible(s);
        item.setManaged(s);

    }

    public PostBlock toPostBlock(String[] inputData){
        //Get Data from input data and store in correct var
        String newCreator = inputData[0];
        String newDate = inputData[1];
        String newTextContent = inputData[2];
        String newSerialCode = inputData[3];

        //Create temporary tutor object
        PostBlock tempPost = new PostBlock(newCreator, newDate, newTextContent, newSerialCode);
        return tempPost;

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
