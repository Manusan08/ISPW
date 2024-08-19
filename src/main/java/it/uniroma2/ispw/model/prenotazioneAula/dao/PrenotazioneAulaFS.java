package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;

import java.sql.SQLException;
import java.util.List;

public class PrenotazioneAulaFS implements PrenotazioneAulaDAO {

    @Override
    public List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam) throws SQLException {
        return List.of();
    }
}
