package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaFS;

public class GestisciPrenotazioneAulaController {
    private AulaDAO aulaDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;

    public GestisciPrenotazioneAulaController() {
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
     public void VisualizzaLeTuePrenotaizoni(){

     }
     public void VisualizzaPrenotazionePerID(){}
    public void ModificaPrenotazione(){}
    public void EliminaPrenotazione(){}
}
