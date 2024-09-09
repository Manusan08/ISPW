package it.uniroma2.ispw.model.prenotazioneaula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

public class PrenotazioneAulaFS implements PrenotazioneAulaDAO {
    @Override
    public boolean esistePrenotazione(String s, Date time, Orario oraLezione) {
        return false;
    }

    @Override
    public boolean salvataggioPrenotazione(String email, String iDaula, Orario oraLezione, Date datalezione, String descrizione, String materia, String nomeProfessore, Date dataFine, String idPrenotazioneAula, Boolean isPrenotata) {
        return false;
    }



    @Override
    public List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam, UserModel usr) throws SQLException {
        return List.of();
    }

    @Override
    public int getCapienzaAula(PrenotazioneAulaModel pab) throws SQLException {
        return 0;
    }

    @Override
    public List<PrenotazioneAulaModel> getAvailableClass() {
        return List.of();
    }
}