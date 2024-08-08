package it.uniroma2.ispw.model.aula.dao;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDBMS implements  AulaDAO{
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
    public AulaModel getAulaById(String nome) throws ItemNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AulaModel aulaModel = new AulaModel();
        try {
            String sql = "select * from Aule where nome=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();

            if(!resultSet.next()) throw new ItemNotFoundException("Nessun corso con nome: " + nome);

            aulaModel.setIdAula(resultSet.getString("IdAula"));
            aulaModel.setPosti(resultSet.getInt("NumeroPosti"));


        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aulaModel;
    }
    public List<AulaModel> getAllAule() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AulaModel> corsoList = new ArrayList<>();
        try {
            String sql = "select * from Aule";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) return corsoList;

            do {
                AulaModel c = new AulaModel(resultSet.getString("IdAule"), resultSet.getInt("Posti"));
                corsoList.add(c);
            } while (resultSet.next());

        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return corsoList;
    }
    public AulaModel getAulaByCognome(String nome) throws ItemNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AulaModel aulaModel = new AulaModel();
        try {
            String sql = "select * from Aule where nomeDocente=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();

            if(!resultSet.next()) throw new ItemNotFoundException("Nessun corso con nome: " + nome);

            aulaModel.setIdAula(resultSet.getString("IdAula"));
            aulaModel.setPosti(resultSet.getInt("NumeroPosti"));


        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aulaModel;
    }
    public AulaModel getAulaByMateria(String nome) throws ItemNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AulaModel aulaModel = new AulaModel();
        try {
            String sql = "select * from Aule where materia=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();

            if(!resultSet.next()) throw new ItemNotFoundException("Nessun corso con nome: " + nome);

            aulaModel.setIdAula(resultSet.getString("IdAula"));
            aulaModel.setPosti(resultSet.getInt("NumeroPosti"));


        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aulaModel;
    }
}
