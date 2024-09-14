package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAOFactoryImpl;

import java.sql.Date;
import java.time.LocalDate;

public class PrenotazioneAulaRicorrentiVerificatore implements PrenotazioneAulaVerificatore {

    private PrenotazioneAulaDAO dao;
    public PrenotazioneAulaRicorrentiVerificatore() {
        PrenotazioneAulaDAOFactoryImpl factory = new PrenotazioneAulaDAOFactoryImpl();
        dao = factory.getDao();
    }

    @Override
    public boolean verificaPrenotazione(PrenotazioneAulaModel prenotazioneAula) {
        if (!prenotazioneAula.isRicorente()) {
            return false;
        }
        LocalDate dataCorrente = prenotazioneAula.getDatalezione();
        LocalDate dataFineLocal = prenotazioneAula.getDataFine();

        while (!dataCorrente.isAfter(dataFineLocal)) {
            if (dao.esistePrenotazione(prenotazioneAula.getIdAula(), Date.valueOf(dataCorrente), prenotazioneAula.getOraLezione())) {
                return false; // L'aula è occupata in una delle date ricorrenti
            }
            dataCorrente = dataCorrente.plusDays(7); // Aggiungi 7 giorni per passare alla settimana successiva
        }

        return true; // L'aula è disponibile per tutte le date ricorrenti
    }
}
