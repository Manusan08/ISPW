package it.uniroma2.ispw.view.cli;


import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.view.cli.docente.DocenteView;
import it.uniroma2.ispw.view.cli.studente.StudenteView;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;

public class CliController {

    public void start() throws SystemException, IOException, LoginException, SQLException, ItemNotFoundException, CampiVuotiExeption {
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
        if (authUsr.getRuolo() == Role.DOCENTE) {
            new DocenteView(authUsr).control();
        } else if (authUsr.getRuolo() == Role.STUDENTE) {
            new StudenteView(authUsr).control();
        }

    }
}