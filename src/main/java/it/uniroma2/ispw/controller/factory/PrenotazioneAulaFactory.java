package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;

public class PrenotazioneAulaFactory {

    public PrenotazioneAula creaPrenotazione(
            PrenotazioneAulaBean prenotazioneAulaBean
    ) {
        if (prenotazioneAulaBean.isRicorente()) {

            return new PrenotazioneRicorrente(prenotazioneAulaBean);
        } else {
            return new PrenotazioneSingola(prenotazioneAulaBean);
        }
    }
}


