package it.uniroma2.ispw.view.graphicalcontroller.studente;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.graphicalcontroller.ControllerGrafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CercaPostoPerIdController extends ControllerGrafico {

    @FXML
    public Button indietroButton;
    @FXML
        private Button avantiid;

        @FXML
        private TextField txtidPrenotazione;

    @FXML
        void avantiBottoneAction(ActionEvent event) {
            //da compleatre

    }

    @Override
    public void inizializza(UserBean cred) {
        //da fare
    }

    public void indietro(ActionEvent event) {

    }
}
