package it.uniroma2.ispw.dao;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.AulaModel;
import it.uniroma2.ispw.utils.db.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDao {
    public List<AulaModel> getAllAuleNumeroPosti(AulaBean aulaBean) throws SystemException {

        List<AulaModel> lista = new ArrayList<>();
        AulaModel aulaModel = null;
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Aule  WHERE posti >= ?;");) {
            ps.setInt(1, aulaBean.getPosti());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aulaModel = new AulaModel();
                aulaModel.setIdAula(rs.getString("idAula"));
                aulaModel.setPosti(rs.getInt("posti"));
                lista.add(aulaModel);
            }
            return lista;

        } catch (SQLException e) {
            SystemException exception = new SystemException();
            exception.initCause(e);
            throw exception;
        }
    }
}
