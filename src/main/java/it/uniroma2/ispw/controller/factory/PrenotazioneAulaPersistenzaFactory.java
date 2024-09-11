package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.controller.factory.strategy.PrenotazioneAulaPersistenza;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public interface PrenotazioneAulaPersistenzaFactory {

     PrenotazioneAulaPersistenza creaPrentazioneAulaPersistence(PrenotazioneAulaModel prenotazioneAulaModel);

    }
