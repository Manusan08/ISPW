package it.uniroma2.ispw.model.aula.dao;


import it.uniroma2.ispw.model.aula.AulaModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;


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
            aulaModel.setProiettore(resultSet.getInt("lim") == 1);
            aulaModel.setComputer(resultSet.getInt("computer") == 1);
            aulaModel.setBanchiDisegno(resultSet.getInt("banchiDisegno") == 1);

        } catch (SystemException | SQLException e) {
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

        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }
        return corsoList;
    }

    @Override
    public List<AulaModel> getAulaByFiltri(AulaModel aulaM) throws ItemNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AulaModel> corsoList = new ArrayList<>();


        try {
            String sql = "select * from aule where posti>=? and lim=? and computer=? and banchiDisegno=? ";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, aulaM.getNumeroPosti());
            int proiettore = aulaM.isProiettore() ? 1 : 0;
            statement.setInt(2, proiettore);

            int computer = aulaM.isComputer() ? 1 : 0;
            statement.setInt(3, computer);

            int banchi = aulaM.isBanchiDisegno() ? 1 : 0;
            statement.setInt(4, banchi);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) throw new ItemNotFoundException("\nnon esistono aule disponibili con i parametri richiesti.");

            do {
                AulaModel c = new AulaModel(resultSet.getString("idAula"));
                c.setNumeroPosti(resultSet.getInt("posti"));
                c.setProiettore(convertIntToBoolean(resultSet.getInt("lim")));
                c.setComputer(convertIntToBoolean(resultSet.getInt("computer")));
                c.setBanchiDisegno(convertIntToBoolean(resultSet.getInt("banchiDisegno")));

                corsoList.add(c);
            } while (resultSet.next());

        } catch (SystemException | SQLException e) {
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


        } catch (SystemException | SQLException e) {
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


        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }

        return aulaModel;
    }
    private boolean convertIntToBoolean(int value) {
        return value == 1;
    }
}
