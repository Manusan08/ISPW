package it.uniroma2.ispw.model.login.dao;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDBMS implements LoginDAO {
    public LoginModel auth(LoginBean loginBean) {
        LoginModel loginModel = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM utenti  WHERE email = ? ";

            statement = ConnectionDB.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, loginBean.getEmail());


            resultSet = statement.executeQuery();
            if(!resultSet.next()) throw new ItemNotFoundException("Credenziali errate!");
            loginModel = setUtenteFromResultSet(resultSet);

        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loginModel;
    }
    private LoginModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        LoginModel loginModel = new LoginModel();



        loginModel.setRole(Role.valueOf(resultSet.getString("role")));
        loginModel.setEmail(resultSet.getString("email"));
        return loginModel;
    }
    public boolean checkIfExists(LoginModel credentialsModel) throws SystemException {
        String query = "SELECT * FROM Utenti WHERE email = ? AND password = ? AND role = ?";
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
