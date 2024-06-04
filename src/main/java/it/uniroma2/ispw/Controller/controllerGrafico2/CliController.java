package it.uniroma2.ispw.Controller.controllerGrafico2;


import it.uniroma2.ispw.bean.UserBean;

public class CliController {

    public void start() {
        UserBean authUsr;
        boolean loopCond= false;
        LoginViewController2 loginView = new LoginViewController2();

        do {
            loginView.control();
            authUsr = loginView.getUsrBean();
            if(authUsr != null)
                loopCond = false;
            else if (loginView.userChoice() == 1) {
                loopCond = true;
            } else {
                System.exit(0);
            }
        } while (loopCond);

        switch (authUsr.getRuolo()) {
           // case DOCENTE -> new StudenteView(authUsr).control();
            //case STUDENTE -> new docenteView(authUsr).control();

        }

    }
}
