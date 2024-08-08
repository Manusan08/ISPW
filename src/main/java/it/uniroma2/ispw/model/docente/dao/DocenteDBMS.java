package it.uniroma2.ispw.model.docente.dao;

import it.uniroma2.ispw.model.docente.DocenteModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocenteDBMS implements DocenteDAO {
    public DocenteModel getDocentebyEmail(String email) throws SystemException {
    String query = "SELECT * FROM Utenti where email = ?;";
    DocenteModel docenteModel = null;
    Connection conn = ConnectionDB.getConnection();

    try (PreparedStatement ps = conn.prepareStatement(query);) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();

        docenteModel = new DocenteModel(rs.getString("email"), rs.getInt("matricola"));


        return docenteModel;
    } catch (SQLException e) {
        SystemException exception = new SystemException();
        exception.initCause(e);
        throw exception;
    }
}
}
