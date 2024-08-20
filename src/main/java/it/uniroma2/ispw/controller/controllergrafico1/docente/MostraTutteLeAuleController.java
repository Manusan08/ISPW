package it.uniroma2.ispw.controller.controllergrafico1.docente;


import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MostraTutteLeAuleController extends ControllerGrafico {
    private UserBean userBean;
    @FXML
    private Button avantiID;

    @FXML
    void avantiBottoneAction(ActionEvent event) {

    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }
}
