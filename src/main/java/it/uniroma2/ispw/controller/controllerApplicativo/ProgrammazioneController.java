package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;

import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;

import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.model.login.dao.LoginFS;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;


public class ProgrammazioneController {


    private LoginDAO loginDAO;

    private AulaDAO aulaDAO;




    public ProgrammazioneController() {
        if(Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {

            loginDAO = new LoginDBMS();

            aulaDAO = new AulaDBMS();

        }
        else {
            try {

                loginDAO = new LoginFS();

                aulaDAO= new AulaFS();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
