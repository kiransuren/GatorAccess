package com.kazza.gatoraccess;


//JavaFX Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Main Class
public class Main extends Application {

    Stage window;       //Create new Stage Window

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));      //Load default screen to root
        window = primaryStage;
        window.setTitle("Gator Access 1.5");                                               //Set Window title
        window.setScene(new Scene(root, 500, 450));                         //Set Window scene and dimensions
        window.show();                                                                     //Show the Window
    }




    public static void main(String[] args) {
        launch(args);
    }
}