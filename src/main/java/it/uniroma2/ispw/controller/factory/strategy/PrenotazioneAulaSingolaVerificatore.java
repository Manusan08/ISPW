package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAOFactory;

import java.sql.Date;

public class PrenotazioneAulaSingolaVerificatore implements PrenotazioneAulaVerificatore {

    private PrenotazioneAulaDAO dao;
    public PrenotazioneAulaSingolaVerificatore() {
        PrenotazioneAulaDAOFactory factory = new PrenotazioneAulaDAOFactory();
        dao = factory.getDao();
    }

    @Override
    public boolean verificaPrenotazione(PrenotazioneAulaModel prenotazioneAula) {
        if (prenotazioneAula.isRicorente()) {
            return false;
        }
        return !dao.esistePrenotazione(prenotazioneAula.getIdAula(), Date.valueOf(prenotazioneAula.getDatalezione()), prenotazioneAula.getOraLezione()); // L'aula è occupata in una delle date ricorrenti
    }
}
