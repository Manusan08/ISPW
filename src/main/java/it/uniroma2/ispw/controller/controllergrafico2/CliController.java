package it.uniroma2.ispw.controller.controllergrafico2;


import it.uniroma2.ispw.controller.controllergrafico2.docente.DocenteView;
import it.uniroma2.ispw.controller.controllergrafico2.studente.StudenteView;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;


import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;

public class CliController {

    public void start() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException, SQLException {
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
            case DOCENTE -> new DocenteView(authUsr).control();
            case STUDENTE -> new StudenteView(authUsr).control();
        }
    }
}