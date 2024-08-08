package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;

import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.docente.dao.DocenteDAO;
import it.uniroma2.ispw.model.docente.dao.DocenteDBMS;
import it.uniroma2.ispw.model.docente.dao.DocenteFS;
import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.model.login.dao.LoginFS;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.studente.dao.StudenteDAO;
import it.uniroma2.ispw.model.studente.dao.StudenteDBMS;
import it.uniroma2.ispw.model.studente.dao.StudenteFS;

public class ProgrammazioneController {


    private LoginDAO loginDAO;

    private AulaDAO aulaDAO;

    private DocenteDAO docenteDAO;

    private StudenteDAO studenteDAO;


    public ProgrammazioneController() {
        if(Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {

            loginDAO = new LoginDBMS();
            studenteDAO = new StudenteDBMS();
            aulaDAO = new AulaDBMS();
            docenteDAO = new DocenteDBMS();
        }
        else {
            try {
                studenteDAO= new StudenteFS();
                loginDAO = new LoginFS();
                docenteDAO= new DocenteFS();
                aulaDAO= new AulaFS();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
