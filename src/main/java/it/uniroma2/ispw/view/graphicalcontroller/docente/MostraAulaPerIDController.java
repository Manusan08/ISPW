package it.uniroma2.ispw.view.graphicalcontroller.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;

import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;

import it.uniroma2.ispw.utils.facade.DocenteFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import it.uniroma2.ispw.utils.ChangePage;

public class MostraAulaPerIDController extends ControllerGrafico {
    private UserBean userBean;

    private DocenteFacade docenteFacade = new DocenteFacade();

    @FXML
    private Button avantiID;

    @FXML
    private TextField idAulaField;

    @FXML
    void avantiAction(ActionEvent event) {
        try {
            String idAula = idAulaField.getText();
            AulaBean aulaBean = docenteFacade.getAulaById(idAula);
            if (aulaBean != null) {
                ChangePage.getChangePage().cambiaPagina("/view/Docente/FiltriInseriti.fxml", userBean, aulaBean);
            } else {
                showAlert("Aula non trovata", "L'ID dell'aula inserito non è valido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore", "Si è verificato un errore durante la ricerca dell'aula.");
        }
    }


    @Override
    public void inizializza(UserBean cred)  {
        this.userBean = cred;
    }
}