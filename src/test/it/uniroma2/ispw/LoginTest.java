package it.uniroma2.ispw;

import it.uniroma2.ispw.model.login.dao.LoginDBMS;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.model.login.LoginModel;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    @Test
    void testcheckIfExists() throws SystemException {
        LoginModel credentialsModel=new LoginModel("utente5","pass1", Role.Docente);
        LoginDBMS loginDAO=new LoginDBMS();

        assertTrue(loginDAO.checkIfExists(credentialsModel));
    }

}