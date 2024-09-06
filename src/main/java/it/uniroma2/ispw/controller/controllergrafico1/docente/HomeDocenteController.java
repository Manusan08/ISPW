package it.uniroma2.ispw.controller.controllergrafico1.docente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class HomeDocenteController extends ControllerGrafico {
    @FXML
    private Button Aulaid;

    @FXML
    private Button gestisciid;
    private UserBean userBean;

    @FXML
    void cercaAulaAction(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Docente/CercaAula.fxml", userBean);
        } catch (SystemException e) {
            // Gestisci l'eccezione
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void gestisciPrenotazioneAction(ActionEvent event) {
        try {
            // Ottieni l'istanza di ChangePage e cambia la pagina
            ChangePage.getChangePage().cambiaPagina("/view/Docente/GestionePosto.fxml", userBean);
        } catch (SystemException e) {
            // Gestisci l'eccezione
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void inizializza(UserBean cred)  {
        this.userBean=cred;

    }
}
