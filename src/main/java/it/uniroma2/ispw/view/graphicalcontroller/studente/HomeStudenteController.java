package it.uniroma2.ispw.view.graphicalcontroller.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class HomeStudenteController extends ControllerGrafico {

    @FXML
    private Button gestisciid;

    @FXML
    private Button prenotaId;

    @FXML
    private Button visualizzaid;
    private UserBean userBean;

    @FXML
    void gestisciPrenotazioneAction(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Studente/GestionePosto.fxml", userBean);
        } catch (SQLException e) {
            getAlert().showAndWait();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }

    @FXML
    void prenotaPostoAction(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Studente/PrenotaPosto.fxml", userBean);
        } catch (SQLException | ItemNotFoundException e) {
            getAlert().showAndWait();
        }
    }


    @FXML
    void visualizzaAuleAction(ActionEvent event) {
        try {

            ChangePage.getChangePage().cambiaPagina("/view/Studente/MostraAuleDisponibili.fxml", userBean);
        } catch (SQLException e) {
          getAlert().showAndWait();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }

    @Override
    public void inizializza(UserBean cred)  {
        this.userBean = cred;

    }


}
