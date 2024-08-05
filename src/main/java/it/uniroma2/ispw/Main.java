package it.uniroma2.ispw;

import it.uniroma2.ispw.controller.controllergrafico2.CliController;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.enums.TypesOfUIs;
import it.uniroma2.ispw.utils.ChangePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends Application {
    private static TypesOfPersistenceLayer persistenceLayer;
    private static TypesOfUIs ui;

    public static TypesOfPersistenceLayer getPersistenceLayer() {
        return persistenceLayer;
    }

    public static void main(String[] args) {
        setPersistenceLayerAndUi();
        if (Main.ui.equals(TypesOfUIs.JAVAFX))
            launch();
        else
            new CliController().start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/LoginProva.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        ChangePage istanza = ChangePage.getChangePage();
        istanza.setStage(stage);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    private static void setPersistenceLayerAndUi() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            //persistence layer
            if (properties.getProperty("persistence.layer").equals("FileSystem")) {
                Main.persistenceLayer = TypesOfPersistenceLayer.FILE_SYSTEM;
            } else {
                Main.persistenceLayer = TypesOfPersistenceLayer.JDBC;
            }

            //user interface
            if (properties.getProperty("ui").equals("javafx")) {
                Main.ui = TypesOfUIs.JAVAFX;
            } else {
                Main.ui = TypesOfUIs.CLI;
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}

