package it.uniroma2.ispw.controller.controllerapplicativo;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;

import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginFS;


import it.uniroma2.ispw.model.login.LoginModel;

import it.uniroma2.ispw.model.login.dao.LoginDBMS;

import it.uniroma2.ispw.utils.exception.ItemNotFoundException;


public class LoginController {


    private LoginDAO loginDAO;

    public LoginController() {
        if (Main.getPersistenceLayer().equals(TypesOfPersistenceLayer.JDBC)) {
            loginDAO = new LoginDBMS();
        } else {
            try {

                loginDAO = new LoginFS();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public UserBean login(LoginBean loginBean) throws ItemNotFoundException {
        LoginModel u = loginDAO.auth(loginBean);
        return new UserBean(u.getEmail(), u.getRole(),u.getNome());
    }
}
