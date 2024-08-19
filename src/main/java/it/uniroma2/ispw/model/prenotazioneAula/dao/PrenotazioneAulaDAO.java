package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PrenotazioneAulaDAO {
    List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam) throws SQLException;
}
