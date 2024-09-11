package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public interface PrenotazioneAulaFactory {
     PrenotazioneAulaModel creaPrenotazione(PrenotazioneAulaBean bean);
}
