package it.uniroma2.ispw.controller.controllergrafico1.docente;



        import it.uniroma2.ispw.bean.UserBean;
        import it.uniroma2.ispw.controller.controllergrafico1.ControllerGrafico;
        import it.uniroma2.ispw.utils.exception.SystemException;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TextField;

public class InserisciFiltriController extends ControllerGrafico {
    private UserBean userBean;
    @FXML
    private ChoiceBox<?> OrarioID;

    @FXML
    private Button avantiID;

    @FXML
    private DatePicker dateFineID;

    @FXML
    private DatePicker dateID;

    @FXML
    private TextField descrizioneID;

    @FXML
    private TextField materiaID;

    @FXML
    private CheckBox ricorrenteID;

    @FXML
    void avantiBottoneAction(ActionEvent event) {

    }

    @Override
    public void inizializza(UserBean cred) throws SystemException {
        this.userBean=cred;
    }
}

