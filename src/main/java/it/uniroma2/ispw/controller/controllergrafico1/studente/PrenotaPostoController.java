package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrenotaPostoController extends ControllerGrafico {

@FXML
private Button cercaAulaID;

@FXML
private Button cercaAulaProfMat;
private UserBean userBean;

@FXML
    void cercaAulaPerIDprenotazione(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaPerId.fxml", userBean);
    } catch (SystemException e) {
        // Gestisci l'eccezione
        e.printStackTrace();
    }

            }

@FXML
    void cercaAulaperProfEMateria(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Studente/CercaConFiltri.fxml", userBean);
    } catch (SystemException e) {
        // Gestisci l'eccezione
        e.printStackTrace();
    }

            }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
    this.userBean=cred;

    }
}
