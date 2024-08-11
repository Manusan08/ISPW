package it.uniroma2.ispw.model.posto.dao;

import it.uniroma2.ispw.model.posto.PostoModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostoDBMS implements PostoDAO{


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

}
