package it.uniroma2.ispw;

import it.uniroma2.ispw.Controller.controllerGrafico2.CliController;
import it.uniroma2.ispw.Enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.Enums.TypesOfUIs;
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
public class App extends Application {
    private static TypesOfPersistenceLayer persistenceLayer;
    private static TypesOfUIs ui;

    public static TypesOfPersistenceLayer getPersistenceLayer() {
        return persistenceLayer;
    }

    public static void main(String[] args) {
        setPersistenceLayerAndUi();
        if (App.ui.equals(TypesOfUIs.JAVAFX))
            launch();
        else
            new CliController().start();

        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/LoginProva.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        ChangePage istanza = ChangePage.getChangePage();
        istanza.setStage(stage);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    private static void setPersistenceLayerAndUi() {
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            //persistence layer
            if (properties.getProperty("persistence.layer").equals("FileSystem")) {
                App.persistenceLayer = TypesOfPersistenceLayer.FILE_SYSTEM;
            } else {
                App.persistenceLayer = TypesOfPersistenceLayer.JDBC;
            }

            //user interface
            if (properties.getProperty("ui").equals("javafx")) {
                App.ui = TypesOfUIs.JAVAFX;
            } else {
                App.ui = TypesOfUIs.CLI;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        App.persistenceLayer = TypesOfPersistenceLayer.JDBC;
        App.ui = TypesOfUIs.JAVAFX;
    }
}

