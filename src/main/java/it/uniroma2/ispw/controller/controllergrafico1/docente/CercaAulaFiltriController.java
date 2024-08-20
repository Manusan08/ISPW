package it.uniroma2.ispw.controller.controllergrafico1.docente;



import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.TextField;

public  class  CercaAulaFiltriController extends ControllerGrafico {
private UserBean userBean;
@FXML
private CheckBox CheckBanchi;

@FXML
private CheckBox CheckComputer;

@FXML
private CheckBox CheckProiettore;

@FXML
private Button avantiBottoneID;

@FXML
private TextField txtNumeroPosti;

@FXML
    void avantiAction(ActionEvent event) {
    try {
        // Ottieni l'istanza di ChangePage e cambia la pagina
        ChangePage.getChangePage().cambiaPagina("/view/Docente/FiltriInseriti.fxml", userBean);
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