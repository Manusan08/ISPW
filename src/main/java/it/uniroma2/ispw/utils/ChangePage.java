package it.uniroma2.ispw.utils;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    public void cambiaPagina(String fxml, UserBean cred) throws SystemException, SQLException {
        cambiaPagina(fxml, cred, null,null,null);  // Passa `null` per i parametri aggiuntivi
    }
    public void cambiaPagina(String fxml, UserBean cred,AulaBean aulaBean) throws SystemException, SQLException {
        cambiaPagina(fxml, cred, aulaBean,null,null);  // Passa `null` per i parametri aggiuntivi
    }
    public void cambiaPagina(String fxml, UserBean cred, List<AulaBean> aulaBeans) throws SystemException, SQLException {
        cambiaPagina(fxml, cred, null,aulaBeans,null);  // Passa `null` per i parametri aggiuntivi
    }

    public void cambiaPagina(String fxml, UserBean cred,PrenotazioneAulaBean pab) throws SystemException, SQLException {
        cambiaPagina(fxml, cred, null,null,pab);  // Passa `null` per i parametri aggiuntivi
    }

    public void cambiaPagina(String fxml, UserBean cred, AulaBean aulaBean, List<AulaBean> aulaBeans,PrenotazioneAulaBean pab) throws SystemException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = null;

        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
        ControllerGrafico controller = loader.getController();    //Uso del polimorfismo, uso una variabile di tipo ControllerGrafico (superclasse)
        controller.inizializza(cred);
        if (aulaBeans != null) {
            controller.setAulaBeans(aulaBeans);
        }
        if(pab != null) {
            controller.setPrenotazioneAulaBeans(pab);
        }
            if (aulaBean != null) {
                controller.setAulaBean(aulaBean);
            }//alla quale in base al pagina caricata associo l'istanza di uno dei controller grafici figli
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

