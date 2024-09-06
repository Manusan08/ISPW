package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class PrenotaPostoController extends ControllerGrafico {

    @FXML
    private Button cercaAulaID;

    @FXML
    private Button cercaAulaProfMat;
    private UserBean userBean;

    @FXML
    void cercaAulaPerIDprenotazione(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaPerId.fxml", userBean);
        } catch (SQLException e) {
            getAlert("ops, qualcosa è andato storto").showAndWait();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }

    @FXML
    void cercaAulaperProfEMateria(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaConFiltri.fxml", userBean);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }



    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;

    }
}
