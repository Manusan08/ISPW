package it.uniroma2.ispw.model.prenotazioneposto.dao;

import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.model.prenotazioneposto.PrenotazionePostoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
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
    private static final String IDPOSTO = "idPosto";
    @Override
    public List<PrenotazionePostoModel> getAllReservations(UserBean us) throws ItemNotFoundException {
        List<PrenotazionePostoModel> prenotazioni = new ArrayList<>();

        ResultSet rs;

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT ppa.nomeProfessore, " +
                    "       p.idPosto, " +
                    "       ppa.idAula, " +
                    "       ppa.materia, " +
                    "       ppa.dataLezione, " +
                    "       ppa.idPrenotazione, " +
                    "       ppa.oraLezione, " +
                    "       ppa.idPrenotazioneAula " +
                    "FROM prenotazioneposto p " +
                    "JOIN professoreprenotaaula ppa ON p.idPrenotazioneAula = ppa.idPrenotazioneAula " +
                    "WHERE p.Utenti_email = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {


            statement.setString(1, us.getEmail());
            rs = statement.executeQuery();

            if (!rs.next()) throw new ItemNotFoundException("non ci sono prenotazioni corrispondenti al tuo account");

            do {
                PrenotazionePostoModel ppm = new PrenotazionePostoModel();

                ppm.setNomeDocente(rs.getString("nomeProfessore"));
                ppm.setIdPosto(rs.getString(IDPOSTO));
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

        }
    }catch (SQLException | SystemException e) {
            throw new RuntimeException(e);
        }
        return prenotazioni;
    }

    @Override
    public void rimuoviPrenotazionePosto(PrenotazionePostoModel ppm) throws ItemNotFoundException {


        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "delete from prenotazioneposto where idPrenotazione=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {


            statement.setString(1, ppm.getIdPrenotazionePosto());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new ItemNotFoundException("nessuna corrispondenza con la prenotazione" + ppm.getIdPrenotazionePosto());
            }
        } }catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PrenotazionePostoModel getPrenotazioneByid(String idPrenotazionePosto) throws ItemNotFoundException {

        PrenotazionePostoModel pp = null;

        ResultSet rs = null;

        try(Connection conn = ConnectionDB.getConnection()) {
            String sql = "select * from prenotazioneposto where idPrenotazione= ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, idPrenotazionePosto);
            rs = statement.executeQuery();


            if (!rs.next()) {
                throw new ItemNotFoundException("non ci sono posti prenotati con questo id:" + idPrenotazionePosto);
            }

            pp = new PrenotazionePostoModel();
            pp.setIdPrenotazionePosto(rs.getString("idPrenotazione"));
            pp.setIdAula(rs.getString("idAula"));
            pp.setIdPosto(rs.getString(IDPOSTO));

        } }catch (SystemException | SQLException e) {
            throw new RuntimeException(e);

        }

        return pp;
    }

    @Override
    public String inserisciPrenotazione(PrenotazionePostoModel ppm) throws SystemException, SQLException {



        try (Connection conn = ConnectionDB.getConnection()){
            String sql = "insert into PrenotazionePosto (" +
                "idPrenotazione," +
                "idAula," +
                "utenti_email," +
                IDPOSTO +
                ",idPrenotazioneAula)" +
                " values (?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ppm.getIdPrenotazionePosto());
            ps.setString(2, ppm.getIdAula());
            ps.setString(3, ppm.getEmail());
            ps.setString(4, ppm.getIdPosto());
            ps.setString(5, ppm.getIdPrenotazioneAula());
            ps.executeUpdate();

            return "La prenotazione è avvenuta con successo!\nid prenotazione :" + ppm.getIdPrenotazionePosto();

        }} catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "Sei già prenotato per questa lezione";
            }
            throw new SQLException("Errore nell'inserimento della prenotazione");

        } catch (SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }
}