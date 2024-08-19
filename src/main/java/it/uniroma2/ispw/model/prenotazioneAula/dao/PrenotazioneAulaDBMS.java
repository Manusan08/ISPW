package it.uniroma2.ispw.model.prenotazioneAula.dao;

import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneAulaDBMS implements PrenotazioneAulaDAO {

    public List<PrenotazioneAulaModel> getPrenotazioniAuleByProfessorAndSubject(PrenotazioneAulaModel pam) throws SQLException {
        List<PrenotazioneAulaModel> prenotazioniAuleModel = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String subName = STR."%\{pam.getNomeProfessore()}%";
        String subSbuject = STR."%\{pam.getMateria()}%";

        try {
            String sql = "select * from	professoreprenotaaula where nomeProfessore like ? and materia like?";
            ps = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, subName);
            ps.setString(2, subSbuject);

            rs = ps.executeQuery();
            if (!rs.next()) {
                return prenotazioniAuleModel;
            }
            do {
                PrenotazioneAulaModel prenotazione = new PrenotazioneAulaModel();


                prenotazione.setMateria(rs.getString("materia"));
                prenotazione.setIdPrenotazioneAula(rs.getString("idPrenotazioneAula"));
                prenotazione.setiDaula(rs.getString("Aule_idAula"));
                prenotazione.setOraLezione(rs.getString("oraLezione"));
                prenotazione.setDatalezione(rs.getDate("dataLezione"));
                prenotazione.setDescrizione(rs.getString("descrizione"));
                prenotazione.setNomeProfessore(rs.getString("nomeProfessore"));
                prenotazioniAuleModel.add(prenotazione);
            } while (rs.next());

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return prenotazioniAuleModel;
    }

}
