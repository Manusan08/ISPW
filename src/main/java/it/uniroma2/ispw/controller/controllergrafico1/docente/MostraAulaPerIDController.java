package it.uniroma2.ispw.controller.controllergrafico1.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;

import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import it.uniroma2.ispw.utils.ChangePage;

public class MostraAulaPerIDController extends ControllerGrafico {
    private UserBean userBean;
    private GestisciAuleController gestisciAuleController = new GestisciAuleController();

    @FXML
    private Button avantiID;

    @FXML
    private TextField idAulaField;

    @FXML
    void avantiAction(ActionEvent event) {
        try {
            String idAula = idAulaField.getText();
            AulaBean aulaBean = gestisciAuleController.getAulaById(idAula);
            if (aulaBean != null) {
                // Pass the aulaBean to the new view
                ChangePage.getChangePage().cambiaPagina("/view/Docente/FiltriInseriti.fxml", userBean, aulaBean);
            } else {
                showAlert("Aula non trovata", "L'ID dell'aula inserito non è valido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore", "Si è verificato un errore durante la ricerca dell'aula.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean = cred;
    }
}
