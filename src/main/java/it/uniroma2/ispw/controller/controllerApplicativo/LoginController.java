package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.model.DocenteModel;
import it.uniroma2.ispw.bean.DocenteBean;
import it.uniroma2.ispw.dao.DocenteDAO;
import it.uniroma2.ispw.utils.Session;
import it.uniroma2.ispw.utils.SessionManager;
import it.uniroma2.ispw.model.LoginModel;
import it.uniroma2.ispw.model.StudenteModel;
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
            case Studente -> loginStudente(cred, loginDAO);
            case Docente ->loginDocente(cred,loginDAO);
        }
    }

    private void loginStudente(LoginBean cred, LoginDAO loginDAO) throws SystemException, LoginException {
        StudenteModel studente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginDAO.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            StudenteDAO studenteDAO = new StudenteDAO();

            studente = studenteDAO.getStudentebyEmail(cred.getEmail());
        //istanziare la singleton
            cred.setIdSession(studente.getCode());
            StudenteBean studenteBean = new StudenteBean(studente);
            SessionManager manager = SessionManager.getSessionManager();
            IdSessioneBean id = new IdSessioneBean(studente.getCode());
            Session sessione = manager.createSession(null, studenteBean, id);
            manager.aggiungiSessione(sessione);
        } else {
            throw new LoginException();

        }
    }
    private void loginDocente(LoginBean cred, LoginDAO loginDAO) throws SystemException, LoginException {
        DocenteModel docente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginDAO.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            DocenteDAO docenteDAO = new DocenteDAO();

            docente = docenteDAO.getDocentebyEmail(cred.getEmail());
            //istanziare la singleton
            cred.setIdSession(docente.getCode());
            DocenteBean docenteBean = new DocenteBean(docente);
            SessionManager manager = SessionManager.getSessionManager();
            IdSessioneBean id = new IdSessioneBean(docente.getCode());
            Session sessione = manager.createSession(docenteBean, null, id);
            manager.aggiungiSessione(sessione);
        } else {
            throw new LoginException();

        }
    }
}
