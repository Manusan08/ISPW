package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaFS;

import java.util.ArrayList;
import java.util.List;

public class GestisciCreazionePrenotazioneAulaController {
    private AulaDAO aulaDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;
    public GestisciCreazionePrenotazioneAulaController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            aulaDAO = new AulaDBMS();
            prenotazioneAulaDAO = new PrenotazioneAulaDBMS();
        } else {
            try {
                aulaDAO = new AulaFS();
                prenotazioneAulaDAO = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
    public void VerificaDisponiblitàByID(String iDAula){

    }
    public PrenotazioneAulaBean PrenotaAulaById(String iDAula){
        PrenotazioneAulaBean prenotazioneAulaBean= new PrenotazioneAulaBean();
        return prenotazioneAulaBean;
    }

    public List<Object> getAuleByMateria(PrenotazioneAulaBean pab) {
        return List.of();
    }

    public List<Object> getAulaByProfessore(PrenotazioneAulaBean pab) {
        return new ArrayList<>();
    }

    public List<Object> getAulaByOrario(PrenotazioneAulaBean pab) {
        return new ArrayList<>();
    }
}
