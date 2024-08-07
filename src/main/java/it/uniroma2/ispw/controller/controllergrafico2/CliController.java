package it.uniroma2.ispw.controller.controllergrafico2;


import it.uniroma2.ispw.controller.controllergrafico2.docente.DocenteView;
import it.uniroma2.ispw.controller.controllergrafico2.studente.StudenteView;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.SystemException;


public class CliController {

    public void start() throws SystemException {
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
            case Docente -> new DocenteView(authUsr).control();
            case Studente -> new StudenteView(authUsr).control();

        }
    }
}