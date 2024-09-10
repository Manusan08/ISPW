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

    public LoginModel auth(LoginBean loginBean) throws ItemNotFoundException{
        LoginModel loginModel = null;
        ResultSet resultSet = null;
        try (Connection conn = ConnectionDB.getConnection()) {
            String query = "SELECT * FROM Utenti WHERE email = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, loginBean.getEmail());
                statement.setString(2, loginBean.getPassword());


                resultSet = statement.executeQuery();
                if (!resultSet.next()) throw new ItemNotFoundException("\nCredenziali errate!");
                loginModel = setUtenteFromResultSet(resultSet);
            }
        } catch (SystemException | SQLException e) {
            throw new RuntimeException(e);
        }
        return loginModel;
    }
    private LoginModel setUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        LoginModel loginModel = new LoginModel();


        loginModel.setRole(Role.valueOf(resultSet.getString("role")));
        loginModel.setEmail(resultSet.getString("email"));
        loginModel.setNome(resultSet.getString("nome"));
        return loginModel;
    }

}
