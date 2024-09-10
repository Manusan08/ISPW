package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAOFactory;

public class PrenotazioneAulaSingolaPersistence implements PrenotazioneAulaPersistenza {

    private PrenotazioneAulaDAO dao;
    public PrenotazioneAulaSingolaPersistence() {
        PrenotazioneAulaDAOFactory factory = new PrenotazioneAulaDAOFactory();
        dao = factory.getDao();
    }

    @Override
    public boolean salvaPrenotazione(PrenotazioneAulaModel prenotazioneAula) {
        if (prenotazioneAula.isRicorente()) {
            return false;
        }

        return dao.salvataggioPrenotazione(
               prenotazioneAula);

    }
}
