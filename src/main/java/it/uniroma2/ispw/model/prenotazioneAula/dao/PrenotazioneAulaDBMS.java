package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;

import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneAulaDBMS implements PrenotazioneAulaDAO {

    public List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam, UserModel usr) throws SQLException, ItemNotFoundException {
        List<PrenotazioneAulaModel> prenotazioniAuleModel = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String subName = "%" + pam.getNomeProfessore() + "%";
        String subSubject = "%" + pam.getMateria() + "%";

        try {
            String sql = "SELECT * FROM professoreprenotaaula WHERE nomeProfessore LIKE ? AND materia LIKE ? " +
                    "AND idPrenotazioneAula NOT IN (SELECT idPrenotazioneAula FROM prenotazioneposto WHERE Utenti_email=?);";

            ps = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, subName);
            ps.setString(2, subSubject);
            ps.setString(3, usr.getEmail());

            rs = ps.executeQuery();

            if (!rs.next()) throw new ItemNotFoundException(
                    "non ci sono corrispondenze con nome " + pam.getNomeProfessore()
                            + " o con materia " + pam.getMateria());
            while (rs.next()) {
                PrenotazioneAulaModel prenotazione = new PrenotazioneAulaModel();
                prenotazione.setMateria(rs.getString("materia"));
                prenotazione.setIdPrenotazioneAula(rs.getString("idPrenotazioneAula"));
                prenotazione.setIdAula(rs.getString("Aule_idAula"));
                prenotazione.setOraLezione(rs.getString("oraLezione"));
                prenotazione.setDatalezione(rs.getDate("dataLezione"));
                prenotazione.setDescrizione(rs.getString("descrizione"));
                prenotazione.setNomeProfessore(rs.getString("nomeProfessore"));

                prenotazioniAuleModel.add(prenotazione);
            }
        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }
        return prenotazioniAuleModel;
    }


    @Override
    public int getCapienzaAula(PrenotazioneAulaModel pam) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int posti = 0;
        try {
            String sql = "select posti from aule where idAula=? ;";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, pam.getIdAula());

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                posti = resultSet.getInt("posti");
            } else {
                throw new RuntimeException("Nessuna corrispondenza trovata con: " + pam.getIdAula());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il controllo della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        return posti;
    }

    @Override
    public List<PrenotazioneAulaModel> getAvailableClass() {
        List<PrenotazioneAulaModel> pams = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT a.idAula, f.oraLezione " +
                    "FROM (SELECT DISTINCT oraLezione FROM stantoPrato.ProfessorePrenotaAula) f " +
                    "CROSS JOIN stantoPrato.Aule a " +
                    "LEFT JOIN stantoPrato.ProfessorePrenotaAula p " +
                    "ON a.idAula = p.Aule_idAula " +
                    "AND p.oraLezione = f.oraLezione " +
                    "AND p.dataLezione = CURDATE() " +
                    "WHERE p.idPrenotazioneAula IS NULL " +
                    "GROUP BY a.idAula, f.oraLezione " +
                    "ORDER BY a.idAula, f.oraLezione;";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PrenotazioneAulaModel pam = new PrenotazioneAulaModel();
                pam.setIdAula(resultSet.getString("idAula"));
                pam.setOraLezione(resultSet.getString("oraLezione"));
                pams.add(pam);
            }
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pams;
    }


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
            if (resultSet == null) {
                esistePrenotazione = true;
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
