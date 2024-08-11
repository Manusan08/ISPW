package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;

import java.sql.SQLException;
import java.util.List;

public class PrenotazionePostoFS implements PrenotazionePostoDAO {
    @Override
    public List<PrenotazionePostoBean> getAulaByMateria(PrenotazionePostoBean postoBean) {
        return List.of();
    }

    @Override
    public List<PrenotazionePostoModel> getAllReservations(PrenotazionePostoBean pb) {
        return List.of();
    }

    @Override
    public void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) {

    }

    @Override
    public PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws SQLException {
        return null;
    }


}
