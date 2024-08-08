package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.bean.DocenteBean;
import it.uniroma2.ispw.model.docente.dao.DocenteDBMS;
import it.uniroma2.ispw.model.docente.dao.DocenteFS;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginFS;
import it.uniroma2.ispw.model.studente.dao.StudenteFS;
import it.uniroma2.ispw.utils.Session;
import it.uniroma2.ispw.utils.SessionManager;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.model.studente.StudenteModel;
import it.uniroma2.ispw.bean.IdSessioneBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.StudenteBean;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.model.studente.dao.StudenteDBMS;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.scene.control.Button;

import javax.security.auth.login.LoginException;

public class LoginController {
    public Button login;

    private LoginDAO loginDAO;
    public void login(LoginBean cred) throws SystemException, LoginException {
        if(Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            loginDAO = new LoginDBMS();
            login1(cred);
        }
        else {
            try {
                loginDAO = new LoginFS();
                login2(cred);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void login1(LoginBean cred) throws SystemException, LoginException {
        LoginDBMS loginDAO = new LoginDBMS();

        switch (cred.getRole()) {
            case Studente -> loginStudenteDBMS(cred, loginDAO);
            case Docente -> loginDocenteFS(cred,loginDAO);
        }
    }


    private void loginStudenteDBMS(LoginBean cred, LoginDBMS loginDAO) throws SystemException, LoginException {
        StudenteModel studente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginDAO.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            StudenteDBMS studenteDBMS = new StudenteDBMS();

            studente = studenteDBMS.getStudentebyEmail(cred.getEmail());
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
    private void loginDocenteFS(LoginBean cred, LoginDBMS loginDAO) throws SystemException, LoginException {
        DocenteModel docente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginDAO.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            DocenteDBMS docenteDBMS = new DocenteDBMS();

            docente = docenteDBMS.getDocentebyEmail(cred.getEmail());
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

    public void login2(LoginBean cred) throws SystemException, LoginException {
        LoginFS loginFS = new LoginFS();

        switch (cred.getRole()) {
            case Studente -> loginStudenteFS(cred, loginFS);
            case Docente -> loginDocenteFS(cred,loginFS);
        }
    }


    private void loginStudenteFS(LoginBean cred, LoginFS loginFS) throws SystemException, LoginException {
        StudenteModel studente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginFS.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            StudenteFS studenteFS = new StudenteFS();

            studente = studenteFS.getStudentebyEmail(cred.getEmail());
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
    private void loginDocenteFS(LoginBean cred, LoginFS loginFS) throws SystemException, LoginException {
        DocenteModel docente = null;
        LoginModel credentialsModel = new LoginModel(cred);
        if (loginFS.checkIfExists(credentialsModel)) {

            //Creare il daoCSV per usare il file system

            DocenteFS docenteFS = new DocenteFS();

            docente = docenteFS.getDocentebyEmail(cred.getEmail());
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
