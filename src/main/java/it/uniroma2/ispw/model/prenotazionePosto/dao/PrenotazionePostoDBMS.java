package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotazionePostoDBMS implements PrenotazionePostoDAO {
    @Override
    public List<PrenotazionePostoBean> getAulaByMateria(PrenotazionePostoBean postoBean) {
        return List.of();
    }

    @Override
    public List<PrenotazionePostoModel> getAllReservations(UserBean us) {
        List<PrenotazionePostoModel> prenotazioni = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try {
            String sql = "select nomeProfessore,\n" +
                    "idPosto,\n" +
                    "idAula,\n" +
                    "materia,dataLezione,\n" +
                    " idPrenotazione,\n" +
                    " ppa.oraLezione,ppa.idPrenotazioneAula\n" +
                    "from \n" +
                    "prenotazioneposto p\n" +
                    "join professoreprenotaaula ppa on p.idPrenotazioneAula=ppa.idPrenotazioneAula\n" +
                    " where p.Utenti_email=?;";


            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, us.getEmail());
            rs = statement.executeQuery();

            if (!rs.next()) {
                return prenotazioni;
            }
            do {
                PrenotazionePostoModel ppm = new PrenotazionePostoModel();

                ppm.setNomeDocente(rs.getString("nomeProfessore"));
                ppm.setIdPosto(rs.getString("idPosto"));
                ppm.setIdAula(rs.getString("idAula"));
                ppm.setMateria(rs.getString("materia"));
                ppm.setGiornoLezione(rs.getDate("dataLezione"));
                ppm.setIdPrenotazionePosto(rs.getString("idPrenotazione"));
                ppm.setIdPrenotazioneAula(rs.getString("idPrenotazioneAula"));

                String orario = rs.getString("OraLezione");
                Orario fasciaOraria = Orario.valueOf(orario);

                ppm.setOraLezione(fasciaOraria.getFasciaOraria());

                prenotazioni.add(ppm);

            } while (rs.next());

            return prenotazioni;
        } catch (
                SystemException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) throws SQLException {
        PreparedStatement statement = null;

        try {
            String sql = "delete from prenotazioneposto where idPrenotazione=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, ppm.getIdPrenotazionePosto());

            statement.executeUpdate();

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


    @Override
    public PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws SQLException {

        PrenotazionePostoModel pp = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            String sql = "select * from prenotazioneposto where idPrenotazione= ?";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, idPrenotazionePosto);
            rs = statement.executeQuery();

            if (!rs.next()) {
                return pp;
            }
            pp = new PrenotazionePostoModel();
            pp.setIdPrenotazionePosto(rs.getString("idPrenotazione"));
            pp.setIdAula(rs.getString("idAula"));
            pp.setIdPosto(rs.getString("idPosto"));

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return pp;
    }

    @Override
    public String inserisciPrenotazione(PrenotazionePostoModel ppm) throws SystemException, SQLException {

        String sql = "insert into PrenotazionePosto (" +
                "idPrenotazione," +
                "idAula," +
                "utenti_email," +
                "idPosto" +
                ",idPrenotazioneAula)" +
                " values (?,?,?,?,?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ppm.getIdPrenotazionePosto());
            ps.setString(2, ppm.getIdAula());
            ps.setString(3, ppm.getEmail());
            ps.setString(4, ppm.getIdPosto());
            ps.setString(5, ppm.getIdPrenotazioneAula());
            ps.executeUpdate();

            return "La prenotazione è avvenuta con successo! id prenotazione :" + ppm.getIdPrenotazionePosto();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "Sei già prenotato per questa lezione";
            }
            throw new SQLException("Errore nell'inserimento della prenotazione");

        } catch (SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }
}