package it.uniroma2.ispw.Controller.controllerGrafico1;

import it.uniroma2.ispw.Controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.Enums.Role;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.GestoreEccezioni;
import it.uniroma2.ispw.utils.exception.RuoloNonSelezionatoException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.security.auth.login.LoginException;

public class LoginViewController1 {
    @FXML
    private ToggleButton studente;
    @FXML
    private ToggleButton docente;
    Label errorMsg;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;

    @FXML
    private void clickLogin() {
        LoginBean loginBean = new LoginBean(email.getText(), password.getText());
        try {
            if (studente.isSelected()) {
                loginBean.setRole(Role.STUDENTE);

            } else if (docente.isSelected()) {
                loginBean.setRole(Role.STUDENTE);

            } else {
                throw new RuoloNonSelezionatoException();
            }

        } catch (RuoloNonSelezionatoException e) {
            GestoreEccezioni.getInstance().handleException(e);
            return;
        }
        LoginController loginController = new LoginController();
        try {
            loginController.login(loginBean);

            if (loginBean != null) {
                //navigate to new page
                ChangePage istanza = ChangePage.getChangePage();
                switch (loginBean.getRole()) {
                    case STUDENTE -> istanza.cambiaPagina("src/main/resources/LoginProva.fxml", null, loginBean);
                    case DOCENTE -> istanza.cambiaPagina("src/main/resources/LoginProva.fxml", null, loginBean);


                }
            }
        } catch (LoginException | SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        }
    }

}
