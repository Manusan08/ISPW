package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.SQLException;
import java.util.List;

public interface PrenotazionePostoDAO {
    List<PrenotazionePostoBean> getAulaByMateria(PrenotazionePostoBean postoBean);
    List<PrenotazionePostoModel> getAllReservations(UserBean userBean);

    void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) throws SQLException;

    PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws SQLException;

    String  inserisciPrenotazione(PrenotazionePostoModel ppm) throws SystemException, SQLException;
}
