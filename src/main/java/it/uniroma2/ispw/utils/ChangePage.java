package it.uniroma2.ispw.Controller.controllerGrafico1;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePage {

    private static ChangePage istanza = null;

    protected ChangePage() {
    }

    public static ChangePage getChangePage() {
        if (ChangePage.istanza == null) {
            ChangePage.istanza = new ChangePage();
        }
        return istanza;
    }

    private Stage stage;

    public void cambiaPagina(String fxml, IdSessioneBean id, LoginBean cred) throws SystemException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = null;

        try {
            scene = new Scene(loader.load(), 1200, 760);
        } catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        ControllerGrafico controller = loader.getController();    //Uso del polimorfismo, uso una variabile di tipo ControllerGrafico (superclasse)
        controller.inizializza(id, cred);   //alla quale in base al pagina caricata associo l'istanza di uno dei controller grafici figli
        this.stage.setScene(scene);                                  //l'operazione inizializza quindi avrà comportamenti diversi in base all'istanza
        this.stage.show();

    }

    public void setStage(Stage stageParam) {
        this.stage = stageParam;
    }

    public Stage getStage() {
        return this.stage;
    }
}

