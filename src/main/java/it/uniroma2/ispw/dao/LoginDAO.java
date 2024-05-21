package it.uniroma2.ispw.dao;

import it.uniroma2.ispw.Model.LoginModel;
import it.uniroma2.ispw.utils.db.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean checkIfExists(LoginModel credentialsModel) throws SystemException {
        String query = "SELECT * FROM Utenti WHERE maeil = ? AND password = ? AND role = ?";
        Connection conn = ConnectionDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setString(1, credentialsModel.getEmail());
            ps.setString(2, credentialsModel.getPassword());
            ps.setString(3, credentialsModel.getRole().name());

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
