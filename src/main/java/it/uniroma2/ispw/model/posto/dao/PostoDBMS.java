package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.model.prenotazioneAula.PrenotazioneAulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostoDBMS implements PostoDAO {


    public void postoNuovamenteDisponibile(PostoModel postoModel) throws SQLException {
        PreparedStatement statement = null;

        try {
            String sql = "update posto set stato=true where idPosto=? and Aule_idAula=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, postoModel.getPostoId());
            statement.setString(2, postoModel.getIdAula());

            statement.executeUpdate();

        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public List<PostoModel> getAvailablePosti(PrenotazioneAulaModel pam) throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<PostoModel> listaPosto = new ArrayList<PostoModel>();

        try {
            String sql ="SELECT idPosto, Aule_idAula\n" +
                    "FROM posto\n" +
                    "WHERE Aule_idAula = (\n" +
                    "    SELECT Aule_idAula\n" +
                    "    FROM professoreprenotaaula\n" +
                    "    WHERE idPrenotazioneAula = ?\n" +
                    ")\n" +
                    "AND idPosto NOT IN (\n" +
                    "    SELECT pp.idPosto\n" +
                    "    FROM prenotazioneposto pp\n" +
                    "    JOIN professoreprenotaaula pf ON pp.idPrenotazioneAula = pf.idPrenotazioneAula\n" +
                    "    WHERE pf.idPrenotazioneAula = ?\n" +
                    ");";


            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, pam.getIdPrenotazioneAula());
            statement.setString(2, pam.getIdPrenotazioneAula());

            rs = statement.executeQuery();


            while (rs.next()) {
                PostoModel postoModel=new PostoModel();
                postoModel.setPostoId(rs.getString("idposto"));
                postoModel.setIdAula(rs.getString("Aule_idAula"));

                listaPosto.add(postoModel);
            }
        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
            return listaPosto;
        }
    }


