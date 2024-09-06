package it.uniroma2.ispw.model.prenotazioneposto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.model.prenotazioneposto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.SQLException;
import java.util.List;

public interface PrenotazionePostoDAO {
    List<PrenotazionePostoBean> getAulaByMateria(PrenotazionePostoBean postoBean);
    List<PrenotazionePostoModel> getAllReservations(UserBean userBean) throws ItemNotFoundException;

    void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) throws SQLException, ItemNotFoundException;

    PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws SQLException, ItemNotFoundException;

    String  inserisciPrenotazione(PrenotazionePostoModel ppm) throws SystemException, SQLException;
}
