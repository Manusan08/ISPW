package it.uniroma2.ispw.model.studente.dao;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.model.studente.StudenteModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudenteDBMS implements StudenteDAO {
    public StudenteModel getStudentebyEmail(String email) throws SystemException {
        String query = "SELECT * FROM Utenti where email = ?;";
        StudenteModel studenteModel = null;
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();

            studenteModel = new StudenteModel(rs.getString("email"), rs.getInt("matricola"));


            return studenteModel;
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
    public StudenteModel auth(LoginBean loginBean) throws ItemNotFoundException {
        StudenteModel studenteModel = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM studente WHERE email = ? ";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, loginBean.getEmail());


            resultSet = statement.executeQuery();
            if(!resultSet.next()) throw new ItemNotFoundException("Credenziali errate!");
            studenteModel = setUtenteFromResultSet(resultSet);

        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studenteModel;
    }
    private StudenteModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        StudenteModel studenteModel = new StudenteModel();
        studenteModel.setNome(resultSet.getString("nome"));

        studenteModel.setCognome(resultSet.getString("cognome"));

        studenteModel.setEmail(resultSet.getString("email"));
        return studenteModel;
    }

}
