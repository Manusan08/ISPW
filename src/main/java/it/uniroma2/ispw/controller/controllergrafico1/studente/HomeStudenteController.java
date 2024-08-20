package it.uniroma2.ispw.controller.controllergrafico1.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    } catch (SystemException e) {
        // Gestisci l'eccezione
        e.printStackTrace();
    }

            }

@FXML
    void prenotaPostoAction(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Studente/PrenotaPosto.fxml", userBean);
    } catch (SystemException e) {
        // Gestisci l'eccezione
        e.printStackTrace();
    }


            }

@FXML
    void visualizzaAuleAction(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        //DA FARE
        ChangePage.getChangePage().cambiaPagina("/view/Studente/sm.fxml", userBean);
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
