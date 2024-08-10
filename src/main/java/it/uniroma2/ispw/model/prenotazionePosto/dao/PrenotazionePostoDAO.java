package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;

import java.util.List;

public interface PrenotazionePostoDAO {
    List<PrenotazionePostoBean> getAulaByMateria(PrenotazionePostoBean postoBean);
    List<PrenotazionePostoModel> getAllReservations(PrenotazionePostoBean pb);
}
