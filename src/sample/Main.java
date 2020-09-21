package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//TODO : Resize font when area is larger than some value
//Backspace Icon made by Google from www.flaticon.com

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 220, 300);

        fxmlLoader.getController();

        primaryStage.setTitle("Calculator");
        scene.getRoot().requestFocus();
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
