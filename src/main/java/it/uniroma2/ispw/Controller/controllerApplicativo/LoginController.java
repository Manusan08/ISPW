package it.uniroma2.ispw.Controller.controllerApplicativo;

import it.uniroma2.ispw.utils.Session;
import it.uniroma2.ispw.utils.SessionManager;
import it.uniroma2.ispw.Model.LoginModel;
import it.uniroma2.ispw.Model.StudenteModel;
import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.StudenteBean;
import it.uniroma2.ispw.dao.LoginDAO;
import it.uniroma2.ispw.dao.StudenteDAO;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.scene.control.Button;

import javax.security.auth.login.LoginException;

public class LoginController {
    public Button login;

    public void login(LoginBean cred) throws SystemException, LoginException {
        LoginDAO loginDAO = new LoginDAO();

        switch (cred.getRole()) {
            case STUDENTE -> loginStudente(cred, loginDAO);
            /*case DOCENTE ->loginDocente(cred,loginDAO);*/


        }
    }

    private void loginStudente(LoginBean cred, LoginDAO loginDAO) throws SystemException, LoginException {
        StudenteModel studente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginDAO.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            StudenteDAO studenteDAO = new StudenteDAO();

            studente = StudenteDAO.getStudentebyEmail(cred.getEmail());
        //istanziare la singleton
            cred.setIdSession(studente.getRole());
            StudenteBean studenteBean = new StudenteBean(studente);
            SessionManager manager = SessionManager.getSessionManager();
            IdSessioneBean id = new IdSessioneBean(studente.getRole());
            Session sessione = manager.createSession(null, null, id);
            manager.aggiungiSessione(sessione);
        } else {
            throw new LoginException();
        }
    }
}
