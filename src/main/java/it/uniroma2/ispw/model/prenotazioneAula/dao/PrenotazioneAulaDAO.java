package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

public interface PrenotazioneAulaDAO {


    boolean esistePrenotazione(String s, Date time, Orario oraLezione);



    boolean salvataggioPrenotazione(String email, String iDaula, Orario oraLezione, Date datalezione, String descrizione, String materia, String nomeProfessore, Date dataFine, String idPrenotazioneAula, Boolean isPrenotata);
    List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam, UserModel usr) throws SQLException, ItemNotFoundException;

    int getCapienzaAula(PrenotazioneAulaModel pab) throws SQLException;

    List<PrenotazioneAulaModel> getAvailableClass();
}
