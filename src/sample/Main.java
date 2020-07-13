package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//TODO : Resize font when area is larger than some value

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 220, 300);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(true);
        scene.getRoot().requestFocus();
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(182);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
