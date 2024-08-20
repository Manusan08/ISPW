package it.uniroma2.ispw.controller.controllergrafico1.studente;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CercaPostoPerNomeController extends ControllerGrafico {
    private UserBean userBean;
    @FXML
    private Button avantiiD;

    @FXML
    private TextField txtMateria;

    @FXML
    private TextField txtNome;

    @FXML
    void avantiBottoneAction(ActionEvent event) {

    }
    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }
}
