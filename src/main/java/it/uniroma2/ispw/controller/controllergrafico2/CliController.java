package it.uniroma2.ispw.controller.controllergrafico2;


import it.uniroma2.ispw.controller.controllergrafico2.docente.DocenteView;
import it.uniroma2.ispw.controller.controllergrafico2.studente.StudenteView;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.Session;
import it.uniroma2.ispw.utils.SessionManager;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class CliController {

    public void start() throws SystemException, InvalidDataException, IOException, LoginException {
        UserBean authUsr;
        boolean loopCond = false;
        LoginViewController2 loginView = new LoginViewController2();


        do {
            loginView.control();
            authUsr = loginView.getUsrBean();

            if (authUsr != null && authUsr.getRuolo() != null)
                loopCond = false;
            else if (loginView.userChoice() == 1) {
                loopCond = true;
            } else {
                System.exit(0);
            }
        } while (loopCond);
        switch (authUsr.getRuolo()) {
            case Docente -> new DocenteView(authUsr).control();
            case Studente -> new StudenteView(authUsr).control();

        }
    }
}