package it.uniroma2.ispw;

import it.uniroma2.ispw.dao.LoginDAO;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.model.LoginModel;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    @Test
    void testcheckIfExists() throws SystemException {
        LoginModel credentialsModel=new LoginModel("utente5","pass1", Role.Docente);
        LoginDAO loginDAO=new LoginDAO();

        assertTrue(loginDAO.checkIfExists(credentialsModel));
    }

}