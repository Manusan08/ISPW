package it.uniroma2.ispw.controller.factory;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

public class PrenotazioneAulaFactoryImpl implements PrenotazioneAulaFactory {

    public PrenotazioneAulaModel creaPrenotazione(PrenotazioneAulaBean bean){
        PrenotazioneAulaModel model = new PrenotazioneAulaModel();
        model.setIdAula(bean.getIdAula());
        model.setEmail(bean.getEmail());
        model.setOraLezione(bean.getOraLezione());
        model.setDatalezione(bean.getGiornoLezione());
        model.setDescrizione(bean.getDescrizione());
        model.setMateria(bean.getMateria());
        model.setIdPrenotazioneAula(bean.getIdPrenotazioneAula());
        model.setNomeProfessore(bean.getNomeDocente());
        model.setRicorente(bean.isRicorente());
        model.setDataFine(bean.getDataFine());
        return model;
    }
}
