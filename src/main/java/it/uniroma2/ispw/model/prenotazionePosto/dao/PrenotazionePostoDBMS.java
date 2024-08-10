package it.uniroma2.ispw.model.prenotazionePosto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.prenotazionePosto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.xml.transform.Result;
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
}
