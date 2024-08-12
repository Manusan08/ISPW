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
            String sql = "SELECT *FROM" +
                    " (SELECT * FROM prenotazioneposto WHERE Utenti_email = ?) " +
                    "pp JOIN professoreprenotaaula ppf ON pp.idAula = ppf.Aule_idAula";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, pb.getEmail());

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
            pp=new PrenotazionePostoModel();
            pp.setIdPrenotazionePosto(rs.getString("idPrenotazione"));
            pp.setIdAula(rs.getString("idAula"));
            pp.setIdPosto(rs.getString("idPosto"));

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            statement.close();

        }
        return pp;
    }

}
