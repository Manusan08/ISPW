package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public interface PrenotazioneAulaPersistenza {
    boolean salvaPrenotazione(PrenotazioneAulaModel prenotazioneAula);
}
