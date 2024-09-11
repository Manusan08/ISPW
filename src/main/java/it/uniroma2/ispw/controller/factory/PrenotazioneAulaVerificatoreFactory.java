package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaVerificatore;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public interface PrenotazioneAulaVerificatoreFactory {
     PrenotazioneAulaVerificatore creaVerificatore(PrenotazioneAulaModel prenotazioneAulaModel);
}

