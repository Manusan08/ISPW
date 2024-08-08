package it.uniroma2.ispw.model.docente.dao;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocenteDBMS implements DocenteDAO {
    @Override
    public DocenteModel getDocentebyEmail(String email) throws SystemException, ItemNotFoundException {
        return null;
    }

    @Override
    public DocenteModel auth(LoginBean loginBean) throws ItemNotFoundException {
    DocenteModel docenteModel = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
        try {
        String query = "SELECT * FROM utente WHERE email = ? AND password = ?";

        statement = ConnectionDB.getInstance().getConnection().prepareStatement(query);
        statement.setString(1, loginBean.getEmail());
        statement.setString(2, loginBean.getPassword());

        resultSet = statement.executeQuery();
        if(!resultSet.next()) throw new ItemNotFoundException("Credenziali errate!");
        docenteModel = setUtenteFromResultSet(resultSet);

    } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docenteModel;
}
    private DocenteModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        DocenteModel docenteModel = new DocenteModel();
        docenteModel.setNome(resultSet.getString("nome"));

        docenteModel.setCognome(resultSet.getString("cognome"));

        docenteModel.setEmail(resultSet.getString("email"));
        return docenteModel;
    }

}

