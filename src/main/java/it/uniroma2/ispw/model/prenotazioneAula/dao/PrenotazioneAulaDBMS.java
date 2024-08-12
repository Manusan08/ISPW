package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.prenotazioneAula.dao.PrenotazioneAulaDAO;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class PrenotazioneAulaDBMS implements PrenotazioneAulaDAO {
    @Override
    public boolean esistePrenotazione(String idAula, Date giornoLezione, Orario oraLezione) {
        boolean esistePrenotazione = false;

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT COUNT(*) FROM professoreprenotaaula WHERE Aule_idAula = ? AND dataLezione = ? AND oraLezione = ?";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, idAula);
            statement.setDate(2, giornoLezione);
            statement.setString(3, String.valueOf(oraLezione));

            resultSet = statement.executeQuery();
            if (resultSet==null){
                esistePrenotazione=true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il controllo della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return esistePrenotazione;
    }

    @Override
    public boolean salvataggioPrenotazione(String email, String iDaula, Orario oraLezione, Date datalezione, String descrizione, String materia, String nomeProfessore, Date dataFine, String idPrenotazioneAula, Boolean isRicorrente) {
        boolean successo = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
                String sql = "INSERT INTO professoreprenotaaula (Utenti_email, Aule_idaula, oraLezione, dataLezione, descrizione, materia, nomeProfessore,  idPrenotazioneAula) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, iDaula);
            statement.setString(3, String.valueOf(oraLezione));
            statement.setDate(4, datalezione);
            statement.setString(5, descrizione);

            statement.setString(6, materia);
            statement.setString(7, nomeProfessore);

            statement.setString(8, RandomStringUtils.randomAlphanumeric(4));


            int rowsAffected = statement.executeUpdate();
            successo = rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return successo;
    }

}
