package it.uniroma2.ispw.Controller.controllerGrafico2;


import it.uniroma2.ispw.Controller.controllerGrafico2.studente.StudenteView;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;

import static it.uniroma2.ispw.Enums.Role.Studente;


public class CliController {

    public void start() {
        UserBean authUsr = null;
        boolean loopCond = false;
        LoginViewController2 loginView = new LoginViewController2();

        LoginBean loginBean;
        do {
            loginBean = loginView.control();

            if (loginBean != null && loginBean.getRole() != null)
                loopCond = false;
            else if (loginView.userChoice() == 1) {
                loopCond = true;
            } else {
                System.exit(0);
            }
        } while (loopCond);
        switch (loginBean.getRole()) {
            // case DOCENTE -> new StudenteView(authUsr).control();
            case Studente -> new StudenteView(authUsr).control();

        }
    }
}