package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaRicorrentiVerificatore;
import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaSingolaVerificatore;
import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaVerificatore;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public class PrenotazioneAulaVerificatoreFactory {

    public PrenotazioneAulaVerificatore creaVerificatore(PrenotazioneAulaModel prenotazioneAulaModel) {
        if (prenotazioneAulaModel.isRicorente()) {
            return new PrenotazioneAulaRicorrentiVerificatore();
        } else {
            return new PrenotazioneAulaSingolaVerificatore();
        }

    }
}
