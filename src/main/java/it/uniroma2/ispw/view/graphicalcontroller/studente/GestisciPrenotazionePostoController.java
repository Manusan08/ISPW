package it.uniroma2.ispw.view.graphicalcontroller.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public  class GestisciPrenotazionePostoController extends ControllerGrafico {

@FXML
private Button eliminaId;

@FXML
private Button visualizzaid;
private UserBean userBean;


@FXML
    void eliminaPrenotazioneAction(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Studente/GestionePosto.fxml", userBean);
    } catch (SQLException e) {
            getAlert().showAndWait();
    } catch (ItemNotFoundException e) {
        getAlert(e.getMessage());
    }

}

@FXML
    void visualizzaPrenotazioneAction(ActionEvent event) {
    try {
        ChangePage.getChangePage().cambiaPagina("/view/Studente/MostraTutteLePrenotazioni.fxml", userBean);
    } catch (SQLException e) {
            getAlert().showAndWait();
    } catch (ItemNotFoundException e) {
        getAlert(e.getMessage());
    }

}

    @Override
    public void inizializza(UserBean cred)  {
    this.userBean=cred;

    }

}
