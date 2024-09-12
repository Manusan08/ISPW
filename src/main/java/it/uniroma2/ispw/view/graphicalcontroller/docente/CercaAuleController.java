package it.uniroma2.ispw.view.graphicalcontroller.docente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public  class CercaAuleController extends ControllerGrafico {
    @FXML
    public Button indietroButton;
    @FXML
private Button cercaAulaID;

@FXML
private Button mostraAuleID;

@FXML
private Button cercAuleID;
private UserBean userBean;

@FXML
    void mostraAuleAction(ActionEvent event) { try {
    // Ottieni l'istanza di ChangePage e cambia la pagina
    ChangePage.getChangePage().cambiaPagina("/view/Docente/MostratutteleAule2.fxml", userBean);
} catch (SQLException | ItemNotFoundException | SystemException e) {
    getAlert().showAndWait();
}

}

@FXML
    void cercaAulaPerFiltriAction(ActionEvent event) {
    try {
    // Ottieni l'istanza di ChangePage e cambia la pagina
    ChangePage.getChangePage().cambiaPagina("/view/Docente/CercaFiltri.fxml", userBean);
} catch (SQLException | ItemNotFoundException | SystemException e) {
        throw new RuntimeException(e);
    }

}

@FXML
    void cercaAulePerID(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Docente/MostraAulePerId.fxml", userBean);
    } catch (SQLException e) {
        getAlert().showAndWait();
    } catch(ItemNotFoundException | SystemException e){
        getAlert(e.getMessage()).showAndWait();
    }

}

    @Override
    public void inizializza(UserBean cred) {
        this.userBean=cred;
    }


    public void indietro(ActionEvent event) {
        try {
            ChangePage.getChangePage().cambiaPagina("/view/HomeDocente.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException | SystemException e) {
            getAlert().showAndWait();
        }
    }
}