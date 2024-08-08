package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.model.docente.dao.DocenteDAO;
import it.uniroma2.ispw.model.docente.dao.DocenteDBMS;
import it.uniroma2.ispw.model.docente.dao.DocenteFS;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginFS;
import it.uniroma2.ispw.model.studente.dao.StudenteDAO;
import it.uniroma2.ispw.model.studente.dao.StudenteFS;
import it.uniroma2.ispw.utils.Session;
import it.uniroma2.ispw.utils.SessionManager;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.model.studente.StudenteModel;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.model.studente.dao.StudenteDBMS;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import javafx.scene.control.Button;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.Socket;

public class LoginController {

    private DocenteDAO docenteDAO;

    private StudenteDAO studenteDAO ;
    private LoginDAO loginDAO;
    public  LoginController( ) throws SystemException, LoginException {
        if(Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            loginDAO = new LoginDBMS();
            studenteDAO = new StudenteDBMS();

            docenteDAO = new DocenteDBMS();

        }
        else {
            try {
                studenteDAO= new StudenteFS();
                loginDAO = new LoginFS();
                docenteDAO= new DocenteFS();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
    public UserBean login(LoginBean loginBean) throws ItemNotFoundException, InvalidDataException {
        String ruolo = String.valueOf(loginBean.getRole());

        LoginModel u = loginDAO.auth(loginBean);
        /*if(ruolo.equals("Studente")){
             u = studenteDAO.auth(loginBean);
            return new UserBean(u.getNome(), u.getCognome(), u.getEmail());
        }
        else if(ruolo.equals("Docente")){
             u = docenteDAO.auth(loginBean);
            return new UserBean(u.getNome(), u.getCognome(), u.getEmail());
        }*/
        if(u==null) {

            throw new ItemNotFoundException("Accesso negato!");
        }
        System.out.println(u.getRole());
        return new UserBean(u.getEmail(),u.getRole());

    }



}
