package it.uniroma2.ispw.controller.controllerApplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.model.aula.dao.PrenotazioneAulaDBMS;
import it.uniroma2.ispw.model.aula.dao.PrenotazioneAulaDao;
import it.uniroma2.ispw.model.aula.dao.PrenotazioneAulaFS;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GestisciPrenotazioneAulaController {
    private PrenotazioneAulaDao prenotazioneAulaDao;



    public GestisciPrenotazioneAulaController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            prenotazioneAulaDao = new PrenotazioneAulaDBMS();
        } else {
            try {
                prenotazioneAulaDao = new PrenotazioneAulaFS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    public List<AulaBean> getAllAule() {
        List<AulaBean> aulaBeanList = new ArrayList<>();
        for (AulaModel c : prenotazioneAulaDao.getAllAule()) {
            AulaBean cb = new AulaBean(c.getIdAula(), c.getNumeroPosti());

            aulaBeanList.add(cb);
        }
        return aulaBeanList;

    }

    public AulaModel getAulaById(String aula) throws ItemNotFoundException {
        return prenotazioneAulaDao.getAulaById(aula);
    }
    

    public void removePrenotazione() {
    }

    public void getAllReservation() {
    }
    

    public boolean prenota() {//questo metodo deve fare la prenotazione dell'aula
        return true;
    }


    public boolean modificaDescrizionePrenotazione(PrenotazioneAulaBean pb) {
        //TODO
        return true;
    }

    public List<AulaBean> getAuleByMateria(PrenotazioneAulaBean pab) {
        return null;
    }
}