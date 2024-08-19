package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.model.posto.PostoModel;
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
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    @Override
    public List<PostoModel> getAvailablePosti(PrenotazioneAulaBean pab) throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<PostoModel> listaPosto = new ArrayList<PostoModel>();

        try {
            String sql ="SELECT idposto, Aule_idAula \n" +
                    "FROM posto \n" +
                    "WHERE Aule_idAula = (\n" +
                    "    SELECT Aule_idAula \n" +
                    "    FROM professoreprenotaaula \n" +
                    "    WHERE idPrenotazioneAula = ?\n" +
                    ") \n" +
                    "AND idposto NOT IN (\n" +
                    "    SELECT p.idposto \n" +
                    "    FROM prenotazioneposto p\n" +
                    ");";


            statement = ConnectionDB.getConnection().prepareStatement(sql);
            statement.setString(1, pab.getIdPrenotazioneAula());

            rs = statement.executeQuery();

            while (rs.next()) {
                PostoModel postoModel=new PostoModel();
                postoModel.setPostoId(rs.getString("idposto"));
                postoModel.setIdAula(rs.getString("Aule_idAula"));
                listaPosto.add(postoModel);
            }
        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
            return listaPosto;
        }
    }
}
