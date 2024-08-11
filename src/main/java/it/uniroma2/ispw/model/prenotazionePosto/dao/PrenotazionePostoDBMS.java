package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

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
    public List<PrenotazionePostoModel> getAllReservations(PrenotazionePostoBean pb) {
        List<PrenotazionePostoModel> prenotazioni = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            String sql = "select * from prenotazioneposto where Utenti_email=? ";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, pb.getEmail());

            rs = statement.executeQuery();

            if (!rs.next()) {
                return prenotazioni;
            }
            do {
                PrenotazionePostoModel ppm = new PrenotazionePostoModel();

                ppm.setNomeDocente(rs.getString("nomeDocente"));
                ppm.setIdPosto(rs.getString("idPosto"));
                ppm.setIdAula(rs.getString("idAula"));
                ppm.setMateria(rs.getString("materia"));
                ppm.setGiornoLezione(rs.getDate("dataLezione"));
                ppm.setIdPrenotazionePosto(rs.getString("idPrenotazione"));


                String orario = rs.getString("Orario");
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
            String sql = "delete from prenotazioneposto where idPosto=? and idAula=?";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, ppm.getIdPosto());
            statement.setString(2, ppm.getIdAula());

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

        PrenotazionePostoModel pp = new PrenotazionePostoModel();
        PreparedStatement statement = null;
        ResultSet rs = null;


        try {
            String sql = "select * from prenotazioneposto where idPosto= : idPrenotazionePosto";
            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            rs = statement.executeQuery();

            pp.setIdPrenotazionePosto(rs.getString(2));
            pp.setIdAula(rs.getString("idAula"));
            pp.setIdPosto(rs.getString("Utenti_email"));
            pp.setIdPrenotazionePosto(rs.getString("idPosto"));

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            statement.close();

        }
        return pp;
    }

}
