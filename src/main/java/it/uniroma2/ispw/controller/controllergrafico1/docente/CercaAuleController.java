package it.uniroma2.ispw.controller.controllergrafico1.docente;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public  class CercaAuleController extends ControllerGrafico {

@FXML
private Button CercaAulaID;

@FXML
private Button MostraAuleID;

@FXML
private Button cercAuleID;
private UserBean userBean;

@FXML
    void MostraAuleAction(ActionEvent event) { try {
    // Ottieni l'istanza di ChangePage e cambia la pagina
    ChangePage.getChangePage().cambiaPagina("/view/Docente/MostratutteleAule2.fxml", userBean);
} catch (SQLException e) {
    throw new RuntimeException(e);
} catch (ItemNotFoundException e) {
    throw new RuntimeException(e);
}

}

@FXML
    void cercaAulaPerFiltriAction(ActionEvent event) {
    try {
    // Ottieni l'istanza di ChangePage e cambia la pagina
    ChangePage.getChangePage().cambiaPagina("/view/Docente/CercaFiltri.fxml", userBean);
} catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ItemNotFoundException e) {
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
    } catch(ItemNotFoundException e){
        getAlert(e.getMessage()).showAndWait();
    }

}

    @Override
    public void inizializza(UserBean cred) {
        this.userBean=cred;
    }


}