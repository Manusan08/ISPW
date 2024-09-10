package it.uniroma2.ispw.model.prenotazioneaula.dao;

import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.UserModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;

import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneAulaDBMS implements PrenotazioneAulaDAO {

    public List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam, UserModel usr) throws SQLException, ItemNotFoundException {
        List<PrenotazioneAulaModel> prenotazioniAuleModel = new ArrayList<>();

        ResultSet rs = null;

        String subName = "%" + pam.getNomeProfessore() + "%";
        String subSubject = "%" + pam.getMateria() + "%";

        try(Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT * FROM professoreprenotaaula WHERE nomeProfessore LIKE ? AND materia LIKE ? " +
                    "AND idPrenotazioneAula NOT IN (SELECT idPrenotazioneAula FROM prenotazioneposto WHERE Utenti_email=?);";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, subName);
                statement.setString(2, subSubject);
                statement.setString(3, usr.getEmail());

                rs = statement.executeQuery();

                if (!rs.next()) throw new ItemNotFoundException(
                        "non ci sono corrispondenze con nome " + pam.getNomeProfessore()
                                + " o con materia " + pam.getMateria());
                while (rs.next()) {
                    PrenotazioneAulaModel prenotazione = new PrenotazioneAulaModel();
                    prenotazione.setMateria(rs.getString("materia"));
                    prenotazione.setIdPrenotazioneAula(rs.getString("idPrenotazioneAula"));
                    prenotazione.setIdAula(rs.getString("Aule_idAula"));
                    prenotazione.setOraLezione(rs.getString("oraLezione"));
                    prenotazione.setDatalezione(rs.getDate("dataLezione").toLocalDate());
                    prenotazione.setDescrizione(rs.getString("descrizione"));
                    prenotazione.setNomeProfessore(rs.getString("nomeProfessore"));

                    prenotazioniAuleModel.add(prenotazione);
                }
            }
        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }
        return prenotazioniAuleModel;
    }


    @Override
    public int getCapienzaAula(PrenotazioneAulaModel pam) {

        ResultSet resultSet = null;
        int posti = 0;
        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "select posti from aule where idAula=? ;";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {



            statement.setString(1, pam.getIdAula());

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                posti = resultSet.getInt("posti");
            } else {
                throw new RuntimeException("Nessuna corrispondenza trovata con: " + pam.getIdAula());
            }

        }} catch (SQLException e) {
            throw new RuntimeException("Errore durante il controllo della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        return posti;
    }

    @Override
    public List<PrenotazioneAulaModel> getAvailableClass() {
        List<PrenotazioneAulaModel> pams = new ArrayList<>();

        ResultSet resultSet = null;

        try (Connection conn = ConnectionDB.getConnection()){
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
            try (PreparedStatement statement = conn.prepareStatement(sql)) {


            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PrenotazioneAulaModel pam = new PrenotazioneAulaModel();
                pam.setIdAula(resultSet.getString("idAula"));
                pam.setOraLezione(resultSet.getString("oraLezione"));
                pams.add(pam);
            }
        } }catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }

        return pams;
    }


    @Override
    public boolean esistePrenotazione(String idAula, Date giornoLezione, Orario oraLezione) {
        boolean esistePrenotazione = false;


        ResultSet resultSet = null;
        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "SELECT COUNT(*) FROM professoreprenotaaula WHERE Aule_idAula = ? AND dataLezione = ? AND oraLezione = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, idAula);
            statement.setDate(2, giornoLezione);
            statement.setString(3, String.valueOf(oraLezione));

            resultSet = statement.executeQuery();
            if (resultSet == null) {
                esistePrenotazione = true;
            }

        } }catch (SQLException e) {
            throw new RuntimeException("Errore durante il controllo della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return esistePrenotazione;
    }

    @Override
    public boolean salvataggioPrenotazione(PrenotazioneAulaModel prenotazione) {
        boolean successo = false;



        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "INSERT INTO professoreprenotaaula (Utenti_email, Aule_idaula, oraLezione, dataLezione, descrizione, materia, nomeProfessore,  idPrenotazioneAula) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, prenotazione.getEmail());
            statement.setString(2, prenotazione.getIdAula());
            statement.setString(3, String.valueOf(prenotazione.getOraLezione()));
            statement.setDate(4, Date.valueOf(prenotazione.getDatalezione()));
            statement.setString(5, prenotazione.getDescrizione());

            statement.setString(6, prenotazione.getMateria());
            statement.setString(7, prenotazione.getNomeProfessore());

            statement.setString(8, RandomStringUtils.randomAlphanumeric(4));


            int rowsAffected = statement.executeUpdate();
            successo = rowsAffected > 0;

        }
    }catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio della prenotazione", e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return successo;
    }

}
