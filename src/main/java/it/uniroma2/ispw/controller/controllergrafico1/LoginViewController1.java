package it.uniroma2.ispw.controller.controllergrafico1;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.utils.ChangePage;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController1 implements Initializable {

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void handleButtonAction(ActionEvent event) {
        try {
            if (!txtUsername.getText().isBlank() && !txtPassword.getText().isBlank()) {
                LoginBean loginBean = new LoginBean(txtUsername.getText(), txtPassword.getText());

                LoginController loginController = new LoginController();
                UserBean userBean = loginController.login(loginBean);

                if (userBean != null) {
                    switch (userBean.getRuolo()) {
                        case DOCENTE -> ChangePage.getChangePage().cambiaPagina("/view/HomeDocente.fxml", userBean);
                        case STUDENTE -> ChangePage.getChangePage().cambiaPagina("/view/HomeStudente.fxml", userBean);
                    }
                }
            }
        } catch (ItemNotFoundException e) {

            getAlert("Credenziali errate o utente inesistente").showAndWait();
        } catch (SQLException | SystemException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private Alert getAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);

        return alert;
    }
}
