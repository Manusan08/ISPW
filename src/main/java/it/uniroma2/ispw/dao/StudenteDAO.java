package it.uniroma2.ispw.dao;

import it.uniroma2.ispw.Model.StudenteModel;
import it.uniroma2.ispw.utils.db.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudenteDAO {
    public static StudenteModel getStudentebyEmail(String email) throws SystemException, SystemException {
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
}