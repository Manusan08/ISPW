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

public class AulaDBMS implements AulaDAO {



    public AulaModel getAulaById(String nome) throws ItemNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AulaModel aulaModel = new AulaModel();
        boolean is;
        try {
            String sql = "select * from Aule where idAula=?";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();

            if(!resultSet.next()) throw new ItemNotFoundException("Nessun corso con nome: " + nome);

            aulaModel.setIdAula(resultSet.getString("idAula"));
            aulaModel.setNumeroPosti(resultSet.getInt("posti"));
            if (resultSet.getInt("lim")==1){
                 is= true;
            }else {is=false;}
            aulaModel.setProiettore(is);
            if (resultSet.getInt("computer")==1){
                is= true;
            }else {is=false;}
            aulaModel.setComputer(is);
            if (resultSet.getInt("banchiDisegno")==1){
                is= true;
            }else {is=false;}
            aulaModel.setBanchiDisegno(is);


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
        boolean is;
        try {
            String sql = "select * from Aule";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) return corsoList;

            do {
                AulaModel aulaModel = new AulaModel();
                aulaModel.setIdAula(resultSet.getString("idAula"));
                aulaModel.setNumeroPosti(resultSet.getInt("posti"));
                if (resultSet.getInt("lim")==1){
                    is= true;
                }else {is=false;}
                aulaModel.setProiettore(is);
                if (resultSet.getInt("computer")==1){
                    is= true;
                }else {is=false;}
                aulaModel.setComputer(is);
                if (resultSet.getInt("banchiDisegno")==1){
                    is= true;
                }else {is=false;}
                aulaModel.setBanchiDisegno(is);
                corsoList.add(aulaModel);
            } while (resultSet.next());

        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return corsoList;
    }

    @Override
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AulaModel> corsoList = new ArrayList<>();
        boolean is;
        int proiettore;
        int computer;
        int banchi;
        try {
            String sql = "select * from aule where posti>=? and lim=? and computer=? and banchiDisegno=? ";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, aulaM.getNumeroPosti());
            if(aulaM.isProiettore()==true){
                 proiettore=1;
            }else{  proiettore=0;}

            statement.setInt(2,proiettore);
            if(aulaM.isComputer()==true){
                computer=1;
            }else{  computer=0;}

            statement.setInt(3,computer);
            if(aulaM.isBanchiDisegno()==true){
                banchi=1;
            }else{  banchi=0;}

            statement.setInt(4,banchi);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) return corsoList;

            do {
                AulaModel c = new AulaModel(resultSet.getString("idAula"));
                c.setNumeroPosti(resultSet.getInt("posti"));
                if (resultSet.getInt("lim")==1){
                    is= true;
                }else {is=false;}
                c.setProiettore(is);
                if (resultSet.getInt("computer")==1){
                    is= true;
                }else {is=false;}
                c.setComputer(is);
                if (resultSet.getInt("banchiDisegno")==1){
                    is= true;
                }else {is=false;}
                c.setBanchiDisegno(is);

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
            aulaModel.setNumeroPosti(resultSet.getInt("NumeroPosti"));


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
            aulaModel.setNumeroPosti(resultSet.getInt("NumeroPosti"));


        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aulaModel;
    }
}
