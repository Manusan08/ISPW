package it.uniroma2.ispw.controller.factory.strategy;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public interface PrenotazioneAulaVerificatore {
    boolean verificaPrenotazione(PrenotazioneAulaModel prenotazioneAula);

}
