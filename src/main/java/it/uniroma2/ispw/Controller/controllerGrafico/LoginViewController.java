package it.uniroma2.ispw.Controller.controllerGrafico;

import it.uniroma2.ispw.Controller.controllerApplicativo.LoginController;
import it.uniroma2.ispw.bean.LoginBean;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {
    Label errorMsg;
    private TextField email;
    private PasswordField password;

    private void onAccediBtnClick(ActionEvent event) {
        try {
            if (!email.getText().isBlank() && !password.getText().isBlank()) {
                LoginBean loginBean = new LoginBean(email.getText(), password.getText());
                LoginController loginController = new LoginController();
                loginController.Login(loginBean);

                if (loginBean != null) {
                    //navigate to new page
                    ChangePage istanza= ChangePage.getChangePage();
                    switch (loginBean.getRole()) {
                        case STUDENTE -> istanza.cambiaPagina("src/main/resources/LoginProva.fxml",null, loginBean);
                        case DOCENTE ->
                                istanza.cambiaPagina(null,null, loginBean);


                    }
                } else {
                    handleError("Credenziali errate!");
                }
            } else handleError("E-mail e password necessarie!");
        } catch (Exception e) {
            handleError(e.getMessage());
        }

    }
    private void handleError(String msg){
        this.errorMsg.setText(msg);
    }
}

