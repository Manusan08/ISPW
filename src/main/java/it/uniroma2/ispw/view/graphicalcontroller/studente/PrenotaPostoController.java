package it.uniroma2.ispw.view.graphicalcontroller.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            ChangePage.getChangePage().cambiaPagina("view/Studente/PrenotaPosto.fxml", userBean);
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
            getAlert().showAndWait();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }



    @Override
    public void inizializza(UserBean cred) {
        this.userBean = cred;

    }

    public void indietro(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/HomeStudente.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException e) {
            getAlert().showAndWait();
        }
    }
}
