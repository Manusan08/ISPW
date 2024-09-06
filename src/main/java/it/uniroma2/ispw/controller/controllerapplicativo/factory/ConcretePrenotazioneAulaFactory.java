package it.uniroma2.ispw.controller.controllerapplicativo.factory;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;

import java.sql.Date;

public class ConcretePrenotazioneAulaFactory {

    public PrenotazioneAula creaPrenotazione(
            PrenotazioneAulaBean prenotazioneAulaBean
    )
    {
        if (prenotazioneAulaBean.isRicorente()) {
            return new PrenotazioneRicorrente(
                     prenotazioneAulaBean.getIdAula(),
                     prenotazioneAulaBean.getEmail(),
                     prenotazioneAulaBean.getOraLezione(),
                    (Date) prenotazioneAulaBean.getGiornoLezione(),
                     prenotazioneAulaBean.getDescrizione(),
                     prenotazioneAulaBean.getMateria(),
                     prenotazioneAulaBean.getNomeDocente(),
                    (Date) prenotazioneAulaBean.getDataFine(),
                    prenotazioneAulaBean.getIdPrenotazioneAula()
                    );
        } else {
            return new PrenotazioneSingola(
                    prenotazioneAulaBean.getIdAula(),
                    prenotazioneAulaBean.getEmail(),
                    prenotazioneAulaBean.getOraLezione(),
                    (Date) prenotazioneAulaBean.getGiornoLezione(),
                    prenotazioneAulaBean.getDescrizione(),
                    prenotazioneAulaBean.getMateria(),
                    prenotazioneAulaBean.getNomeDocente(),
                    prenotazioneAulaBean.getIdPrenotazioneAula()
            );

        }


    }
}


