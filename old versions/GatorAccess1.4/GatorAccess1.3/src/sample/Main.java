package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("tutorWindow.fxml"));
        window = primaryStage;
        window.setTitle("Gator Access 1.4");
        window.setScene(new Scene(root, 1050, 700));
        window.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
