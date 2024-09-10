package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.prenotazioneaula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostoDBMS implements PostoDAO {


    public void postoNuovamenteDisponibile(PostoModel postoModel) throws SQLException {


        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "update posto set stato=true where idPosto=? and Aule_idAula=?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, postoModel.getPostoId());
            statement.setString(2, postoModel.getIdAula());

            statement.executeUpdate();

        }} catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PostoModel> getAvailablePosti(PrenotazioneAulaModel pam) throws SQLException {

        ResultSet rs = null;
        List<PostoModel> listaPosto = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()){
            String sql = """
            SELECT p.idPosto, p.Aule_idAula
            FROM posto p
            WHERE p.Aule_idAula = (
                SELECT pa.Aule_idAula
                FROM professoreprenotaaula pa
                WHERE pa.idPrenotazioneAula = ?
            )
            AND p.idPosto NOT IN (
                SELECT pp.idPosto
                FROM prenotazioneposto pp
                JOIN professoreprenotaaula pf ON pp.idPrenotazioneAula = pf.idPrenotazioneAula
                WHERE pf.idPrenotazioneAula = ?
            );
            """;


            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, pam.getIdPrenotazioneAula());
            statement.setString(2, pam.getIdPrenotazioneAula());

            rs = statement.executeQuery();


            while (rs.next()) {
                PostoModel postoModel=new PostoModel();
                postoModel.setPostoId(rs.getString("idposto"));
                postoModel.setIdAula(rs.getString("Aule_idAula"));

                listaPosto.add(postoModel);
            }
        } }catch (SystemException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
            return listaPosto;
        }
    }


