package it.uniroma2.ispw.view.graphicalcontroller.studente;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CercaPostoPerNomeController extends ControllerGrafico {
    @FXML
    public Button avantiiD;
    @FXML
    public Button indietroButton;
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
        } catch (SQLException e) {
                getAlert();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }

    }
    @Override
    public void inizializza(UserBean cred)  {
        this.userBean=cred;
    }

    public void annullaBottoneAction(ActionEvent event) {
        try {
            PrenotazioneAulaBean pab =new PrenotazioneAulaBean();
            pab.setNomeDocente(txtNome.getText());
            pab.setMateria(txtMateria.getText());

            ChangePage.getChangePage().cambiaPagina("/view/Studente/MostraAuleDaPrenotare.fxml",userBean ,pab);
        } catch (SQLException e) {
            getAlert("qualcosa è andato storto").showAndWait();
        } catch (ItemNotFoundException e) {
            getAlert(e.getMessage()).showAndWait();
        }
    }

    public void indietro(ActionEvent event) {

        try {
            ChangePage.getChangePage().cambiaPagina("/view/Studente/PrenotaPosto.fxml", this.userBean);
        } catch (SQLException | ItemNotFoundException e) {
            getAlert().showAndWait();
        }
    }
}
