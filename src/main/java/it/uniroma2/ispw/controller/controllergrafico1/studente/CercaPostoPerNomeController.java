package it.uniroma2.ispw.controller.controllergrafico1.studente;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CercaPostoPerNomeController extends ControllerGrafico {
    @FXML
    public Button avantiiD;
    private UserBean userBean;
    @FXML
    private Button annullaId;

    @FXML
    private TextField txtMateria;

    @FXML
    private TextField txtNome;

    @FXML
    void avantiBottoneAction(ActionEvent event) {
        try {
            PrenotazioneAulaBean pab =new PrenotazioneAulaBean();
            pab.setNomeDocente(txtNome.getText());
            pab.setMateria(txtMateria.getText());

            ChangePage.getChangePage().cambiaPagina("/view/Studente/MostraAuleDaPrenotare.fxml",userBean, pab);
        } catch (SystemException e) {
            // Gestisci l'eccezione
            e.getCause();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }

    public void annullaBottoneAction(ActionEvent event) {
        try {
            PrenotazioneAulaBean pab =new PrenotazioneAulaBean();
            pab.setNomeDocente(txtNome.getText());
            pab.setMateria(txtMateria.getText());

            ChangePage.getChangePage().cambiaPagina("/view/Studente/MostraAuleDaPrenotare.fxml",userBean ,pab);
        } catch (SystemException e) {
            // Gestisci l'eccezione
            e.getCause();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
