package it.uniroma2.ispw;

import it.uniroma2.ispw.Controller.controllerGrafico.ChangePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("src/main/resources/LoginProva.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 760);
        ChangePage istanza=ChangePage.getChangePage();
        istanza.setStage(stage);
        stage.setTitle("PlayBasket!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}