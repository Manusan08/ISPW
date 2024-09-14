package it.uniroma2.ispw.model.prenotazioneposto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.prenotazioneposto.PrenotazionePostoModel;

import java.sql.SQLException;
import java.util.List;

public class PrenotazionePostoFS implements PrenotazionePostoDAO {

    @Override
    public List<PrenotazionePostoModel> getAllReservations(UserModel us) {
        return List.of();
    }

    @Override
    public void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) {
        // da implementare
    }

    @Override
    public PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws SQLException {
        return null;
    }

    @Override
    public String inserisciPrenotazione(PrenotazionePostoModel ppm) {
        return null;
    }


}
