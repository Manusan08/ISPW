package it.uniroma2.ispw.model.prenotazioneaula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PrenotazioneAulaDAO {


    boolean esistePrenotazione(String s, Date time, Orario oraLezione);



    boolean salvataggioPrenotazione(PrenotazioneAulaModel prenotazioneAulaModel);
    List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam, UserModel usr) throws ItemNotFoundException, SystemException;

    int getCapienzaAula(PrenotazioneAulaModel pab) throws SQLException;

    List<PrenotazioneAulaModel> getAvailableClass();
}
