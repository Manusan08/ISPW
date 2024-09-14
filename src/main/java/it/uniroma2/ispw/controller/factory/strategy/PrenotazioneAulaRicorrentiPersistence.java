package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAOFactoryImpl;

import java.time.LocalDate;

public class PrenotazioneAulaRicorrentiPersistence implements PrenotazioneAulaPersistenza {

    private PrenotazioneAulaDAO dao;

    public PrenotazioneAulaRicorrentiPersistence() {
        PrenotazioneAulaDAOFactoryImpl factory = new PrenotazioneAulaDAOFactoryImpl();
        dao = factory.getDao();
    }
    @Override
    public boolean salvaPrenotazione(PrenotazioneAulaModel prenotazioneAula) {
        if (!prenotazioneAula.isRicorente()) {
            return false;
        }

        LocalDate dataCorrente = prenotazioneAula.getDatalezione();
        LocalDate dataFineLocal = prenotazioneAula.getDataFine();

        while (!dataCorrente.isAfter(dataFineLocal)) {
            if (!dao.salvataggioPrenotazione(
                    prenotazioneAula)) {
                return false;
            }
            dataCorrente = dataCorrente.plusDays(7);
        }
        return true;
    }
}
