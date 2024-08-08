package it.uniroma2.ispw.controller.controllergrafico1;

import it.uniroma2.ispw.controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.*;
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
    private void clickLogin() throws SystemException, LoginException {
        LoginBean loginBean = new LoginBean(email.getText(), password.getText());
        try {
            if (studente.isSelected()) {
                loginBean.setRole(Role.Studente);

            } else if (docente.isSelected()) {
                loginBean.setRole(Role.Studente);

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
                    case Studente -> istanza.cambiaPagina("src/main/resources/LoginProva.fxml", null, loginBean);
                    case Docente -> istanza.cambiaPagina("src/main/resources/LoginProva.fxml", null, loginBean);


                }
            }
        } catch (SystemException e) {
            GestoreEccezioni.getInstance().handleException(e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
