/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatoraccess1.pkg0;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GatorAccess
 * Created on the 6th of October 2018
 * By Kiran Surendran
 *  
 * VERSION 1.1
 */


public class MainGUI extends Application implements EventHandler<ActionEvent> {
  
    //Intitialization of window and scenes
    Stage window;
    Scene feedScene, msgScene, tutScene, profScene;
    
    
    //CONSTANTS
    int vgap = 10;
    int hgap = 10;
    int windowWidth = 1000;
    int windowHeight = 700;
           

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Window initialization
        primaryStage.setTitle("Gator Access 1.1");
        window = primaryStage;
        
        //Important Variable decleration
        
      

//DEPRECATED CODE IGNORE - OLD SIDEBAR CODE START
//        //SIDEBAR START
//        //Feed Button
//        Button feedButton = new Button("Feed");                                         //Create new button
//        feedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
//        GridPane.setConstraints(feedButton, 0, 0);                                      //Location on GridPane
//        feedButton.getStyleClass().add("feed-button-static");                           //Set styleclass
//        
//        //Messages Button   
//        Button messageButton = new Button("Messages");                                  //Create new button
//        messageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
//        GridPane.setConstraints(messageButton, 0, 1);                                   //Location on GridPane
//        messageButton.getStyleClass().add("msg-button-static");                         //Set styleclass
//        
//        //TutorFind Button
//        Button tutorButton = new Button("TutorFind");                                   //Create new button
//        tutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
//        GridPane.setConstraints(tutorButton, 0, 2);                                     //Location on GridPane
//        tutorButton.getStyleClass().add("tut-button-static");                           //Set styleclass
//        
//        //Profile Button
//        Button profileButton = new Button("Profile");                                   //Create new button
//        profileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
//        GridPane.setConstraints(profileButton, 0, 3);                                   //Location on GridPane
//        profileButton.getStyleClass().add("prof-button-static");                        //Set styleclass
//        //SIDEBAR END
//DEPRECATED CODE IGNORE - OLD SIDEBAR CODE  END   
        
        
//        //StackPane for side bar
//        VBox sideBar = new VBox();
//        //SIDEBAR START
//        //Feed Button
//        Button feedButton = new Button("Feed");                                         //Create new button
//        feedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
//        //GridPane.setConstraints(feedButton, 0, 0);                                      //Location on GridPane
//        feedButton.getStyleClass().add("feed-button-static");                           //Set styleclass
//        
//        //Messages Button   
//        Button messageButton = new Button("Messages");                                  //Create new button
//        messageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
//        //GridPane.setConstraints(messageButton, 0, 1);                                   //Location on GridPane
//        messageButton.getStyleClass().add("msg-button-static");                         //Set styleclass
//        
//        //TutorFind Button
//        Button tutorButton = new Button("TutorFind");                                   //Create new button
//        tutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
//        //GridPane.setConstraints(tutorButton, 0, 2);                                     //Location on GridPane
//        tutorButton.getStyleClass().add("tut-button-static");                           //Set styleclass
//        
//        //Profile Button
//        Button profileButton = new Button("Profile");                                   //Create new button
//        profileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
//        //GridPane.setConstraints(profileButton, 0, 3);                                   //Location on GridPane
//        profileButton.getStyleClass().add("prof-button-static");                        //Set styleclass
//        //SIDEBAR END
//        sideBar.getChildren().addAll(feedButton, messageButton, tutorButton, profileButton);
//        GridPane.setConstraints(sideBar, 0, 0);
        
        







        //FEED SCENE GUI START
        //GridPane/Layout Initialization and settings
        GridPane feedGrid = new GridPane();
        VBox fsideBar = new VBox();
        feedGrid.setPadding(new Insets(0,10,10,0));
        feedGrid.setVgap(vgap);
        feedGrid.setHgap(hgap);
        //Create all components
        //SIDEBAR START
        //Feed Button
        Button ffeedButton = new Button("Feed");                                         //Create new button
        ffeedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
        GridPane.setConstraints(ffeedButton, 0, 0);                                      //Location on GridPane
        ffeedButton.getStyleClass().add("feed-button-selected");                           //Set styleclass
        //Messages Button   
        Button fmessageButton = new Button("Messages");                                  //Create new button
        fmessageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
        GridPane.setConstraints(fmessageButton, 0, 1);                                   //Location on GridPane
        fmessageButton.getStyleClass().add("msg-button-static");                         //Set styleclass
        //TutorFind Button
        Button ftutorButton = new Button("TutorFind");                                   //Create new button
        ftutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
        GridPane.setConstraints(ftutorButton, 0, 2);                                     //Location on GridPane
        ftutorButton.getStyleClass().add("tut-button-static");                           //Set styleclass
        //Profile Button
        Button fprofileButton = new Button("Profile");                                   //Create new button
        fprofileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
        GridPane.setConstraints(fprofileButton, 0, 3);                                   //Location on GridPane
        fprofileButton.getStyleClass().add("prof-button-static");                        //Set styleclass
        fsideBar.getChildren().addAll(ffeedButton, fmessageButton, ftutorButton, fprofileButton);
        GridPane.setConstraints(fsideBar, 0, 0);
        //SIDEBAR END
        //Get all components and gridpane
        feedGrid.getChildren().add(fsideBar);
        feedScene = new Scene(feedGrid, windowWidth, windowHeight);        //Create new scene with Grid Pane
        feedScene.getStylesheets().add("gatoraccess1/pkg0/NotViper.css");
        //FEED SCENE GUI END
        
        //Message SCENE GUI START
        //GridPane/Layout Initialization and settings
        GridPane msgGrid = new GridPane();
        VBox msideBar = new VBox();
        msgGrid.setPadding(new Insets(0,10,10,0));
        msgGrid.setVgap(vgap);
        msgGrid.setHgap(50);
        //Create all components
        //SIDEBAR START
        //Feed Button
        Button mfeedButton = new Button("Feed");                                         //Create new button
        mfeedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
        GridPane.setConstraints(mfeedButton, 0, 0);                                      //Location on GridPane
        mfeedButton.getStyleClass().add("feed-button-static");                           //Set styleclass
        //Messages Button   
        Button mmessageButton = new Button("Messages");                                  //Create new button
        mmessageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
        GridPane.setConstraints(mmessageButton, 0, 1);                                   //Location on GridPane
        mmessageButton.getStyleClass().add("msg-button-selected");                         //Set styleclass
        //TutorFind Button
        Button mtutorButton = new Button("TutorFind");                                   //Create new button
        mtutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
        GridPane.setConstraints(mtutorButton, 0, 2);                                     //Location on GridPane
        mtutorButton.getStyleClass().add("tut-button-static");                           //Set styleclass
        //Profile Button
        Button mprofileButton = new Button("Profile");                                   //Create new button
        mprofileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
        GridPane.setConstraints(mprofileButton, 0, 3);                                   //Location on GridPane
        mprofileButton.getStyleClass().add("prof-button-static");                        //Set styleclass
        msideBar.getChildren().addAll(mfeedButton, mmessageButton, mtutorButton, mprofileButton);
        GridPane.setConstraints(msideBar, 0, 0);
        //SIDEBAR END
        //Message List
        ListView<String> msgList;
        msgList = new ListView<>();
        msgList.getItems().addAll("Steve the Physics Guy", "Marie the Chemistry Dude", "William the English Man");
        GridPane.setConstraints(msgList, 1,0);
        msgList.getStyleClass().add("msg-list");
        msgList.setMinWidth(600);
        msgList.setMinHeight(200);
        //Get all components and gridpane
        msgGrid.getChildren().addAll(msideBar, msgList);
        msgScene = new Scene(msgGrid, windowWidth, windowHeight);        //Create new scene with Grid Pane
        msgScene.getStylesheets().add("gatoraccess1/pkg0/NotViper.css");
        //Message SCENE GUI END
        
        
        
        
        
        //TutorFind SCENE GUI START
        //GridPane/Layout Initialization and settings
        GridPane tutGrid = new GridPane();
        VBox tsideBar = new VBox();
        tutGrid.setPadding(new Insets(0,0,0,0));
        tutGrid.setVgap(vgap);
        tutGrid.setHgap(hgap);
        //Create all compenents
        //SIDEBAR START
        //Feed Button
        Button tfeedButton = new Button("Feed");                                         //Create new button
        tfeedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
        tfeedButton.getStyleClass().add("feed-button-static");                           //Set styleclass
        //Messages Button   
        Button tmessageButton = new Button("Messages");                                  //Create new button
        tmessageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
        tmessageButton.getStyleClass().add("msg-button-static");                         //Set styleclass
        //TutorFind Button
        Button ttutorButton = new Button("TutorFind");                                   //Create new button
        ttutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
        ttutorButton.getStyleClass().add("tut-button-selected");                           //Set styleclass
        //Profile Button
        Button tprofileButton = new Button("Profile");                                   //Create new button
        tprofileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
        tprofileButton.getStyleClass().add("prof-button-static");                        //Set styleclass
        tsideBar.getChildren().addAll(tfeedButton, tmessageButton, ttutorButton, tprofileButton);
        GridPane.setConstraints(tsideBar, 0, 0);
        //SIDEBAR END
        //Search Bar and search button START
        //Srch Bar
        TextField userSearch = new TextField();
        userSearch.setPromptText("Search for a course name or code");
        GridPane.setConstraints(userSearch, 1, 0);
        userSearch.getStyleClass().add("tutor-search-bar");
        userSearch.setMinWidth(500);
        userSearch.setMinHeight(30);
        //Srch button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> findTutor(userSearch.getText()));
        GridPane.setConstraints(searchButton, 2, 0);
        searchButton.getStyleClass().add("searchButton");
        //Search Bar and search button END
        //Get all components and gridpane
        tutGrid.getChildren().addAll(tsideBar, userSearch, searchButton);
        tutScene = new Scene(tutGrid, windowWidth, windowHeight);        //Create new scene with Grid Pane
        tutScene.getStylesheets().add("gatoraccess1/pkg0/NotViper.css");
        //TutorFind SCENE GUI END
        System.out.println("WorkingNEW");
        
        //Profile SCENE GUI START
        //GridPane/Layout Initialization and settings
        GridPane profGrid = new GridPane();
        VBox psideBar = new VBox();
        profGrid.setPadding(new Insets(0,10,10,0));
        profGrid.setVgap(vgap);
        profGrid.setHgap(hgap);
        //Create all components
        //SIDEBAR START
        //Feed Button
        Button pfeedButton = new Button("Feed");                                         //Create new button
        pfeedButton.setOnAction(e -> changeScene(feedScene));         //On button pressed 
        GridPane.setConstraints(pfeedButton, 0, 0);                                      //Location on GridPane
        pfeedButton.getStyleClass().add("feed-button-static");                           //Set styleclass
        //Messages Button   
        Button pmessageButton = new Button("Messages");                                  //Create new button
        pmessageButton.setOnAction(e -> changeScene(msgScene));  //On button pressed
        GridPane.setConstraints(pmessageButton, 0, 1);                                   //Location on GridPane
        pmessageButton.getStyleClass().add("msg-button-static");                         //Set styleclass
        //TutorFind Button
        Button ptutorButton = new Button("TutorFind");                                   //Create new button
        ptutorButton.setOnAction(e -> changeScene(tutScene));   //On button pressed
        GridPane.setConstraints(ptutorButton, 0, 2);                                     //Location on GridPane
        ptutorButton.getStyleClass().add("tut-button-static");                           //Set styleclass
        //Profile Button
        Button pprofileButton = new Button("Profile");                                   //Create new button
        pprofileButton.setOnAction(e -> changeScene(profScene));   //On button pressed
        GridPane.setConstraints(pprofileButton, 0, 3);                                   //Location on GridPane
        pprofileButton.getStyleClass().add("prof-button-selected");                        //Set styleclass
        psideBar.getChildren().addAll(pfeedButton, pmessageButton, ptutorButton, pprofileButton);
        GridPane.setConstraints(psideBar, 0, 0);
        //SIDEBAR END
        //Get all components and gridpane
        profGrid.getChildren().addAll(psideBar);
        profScene = new Scene(profGrid, 1000, 700);        //Create new scene with Grid Pane
        profScene.getStylesheets().add("gatoraccess1/pkg0/NotViper.css");
        //Profile SCENE GUI END
        
        
       
        
        //Set main scene and show on window
        changeScene(feedScene);
        window.setScene(feedScene);
        window.show();
        
        //DEBUGGING PURPOSES ONLY
        //SHOW GRID LINES
        feedGrid.setGridLinesVisible(true);
        msgGrid.setGridLinesVisible(true);
        tutGrid.setGridLinesVisible(true);
        profGrid.setGridLinesVisible(true);
        
        
        
        
        
        
    }

    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void changeScene(Scene s){   //Changes scene to requested scene and shows on window
        window.setScene(s);
        window.show();
        System.out.println("Changed Scene");
    }
    
    public void findTutor(String userInput){
        System.out.println("Searched for" + userInput);
    }
    
    
}














