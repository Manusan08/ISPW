package it.uniroma2.ispw.controller;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.*;
import it.uniroma2.ispw.enums.TypesOfPersistenceLayer;

import it.uniroma2.ispw.model.login.dao.LoginDAO;
import it.uniroma2.ispw.model.login.dao.LoginDAOFactory;
import it.uniroma2.ispw.model.login.dao.LoginFS;


import it.uniroma2.ispw.model.login.LoginModel;

import it.uniroma2.ispw.model.login.dao.LoginDBMS;

import it.uniroma2.ispw.model.prenotazioneaula.dao.PrenotazioneAulaDAOFactory;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;

import java.io.IOException;


public class LoginController {


    private final LoginDAO loginDAO;

    public LoginController() throws IOException {
        LoginDAOFactory daoFactory = new LoginDAOFactory();
        loginDAO = daoFactory.getDao();
    }
    public UserBean login(LoginBean loginBean) throws ItemNotFoundException {
        LoginModel u = loginDAO.auth(loginBean);

        return new UserBean(u.getEmail(), u.getRole(),u.getNome());
    }
}
