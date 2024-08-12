package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.factory.ConcretePrenotazioneAulaFactory;
import it.uniroma2.ispw.controller.controllerApplicativo.factory.PrenotazioneAula;
import it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula.ConfermPrenotazioneView;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.dao.AulaDAO;
import it.uniroma2.ispw.model.aula.dao.AulaDBMS;
import it.uniroma2.ispw.model.aula.dao.AulaFS;
import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaFS;

import java.sql.Date;
import java.util.List;

public class GestisciCreazionePrenotazioneAulaController {
    private AulaDAO aulaDAO;
    private PrenotazioneAulaDAO prenotazioneAulaDAO;
    private ConcretePrenotazioneAulaFactory prenotazioneFactory;

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
        this.prenotazioneFactory = new ConcretePrenotazioneAulaFactory();
    }





    public boolean prenota(PrenotazioneAulaBean prenotazione) {
        ConcretePrenotazioneAulaFactory factory = new ConcretePrenotazioneAulaFactory();
        PrenotazioneAula prenotazioneAula = factory.creaPrenotazione(prenotazione);
        if (prenotazioneAula.verificaPrenotazione()) {
            ConfermPrenotazioneView.confermaPrenotazione(prenotazione);
            prenotazioneAula.salvaPrenotazione();
            return true;
        } return false;

    }


    public boolean modificaDescrizionePrenotazione(PrenotazioneAulaBean pb) {
        return true;
    }

    public void removePrenotazione() {
    }

    public List<Object> getPrenotazioneByName(UserBean us) {
        return null;
    }
}
