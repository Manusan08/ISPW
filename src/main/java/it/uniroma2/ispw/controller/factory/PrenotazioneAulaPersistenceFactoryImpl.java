package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaPersistenza;
import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaRicorrentiPersistence;
import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaSingolaPersistence;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public class PrenotazioneAulaPersistenceFactoryImpl implements PrenotazioneAulaPersistenzaFactory {

    public PrenotazioneAulaPersistenza creaPrentazioneAulaPersistence(PrenotazioneAulaModel prenotazioneAulaModel) {
        if (prenotazioneAulaModel.isRicorente()) {
            return new PrenotazioneAulaRicorrentiPersistence();
        } else {
            return new PrenotazioneAulaSingolaPersistence();
        }
    }
}


