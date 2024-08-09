package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Role;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

import static org.junit.jupiter.api.Assertions.*;

class StudenteViewTest {
    @Test
    void studentTest() throws SystemException, InvalidDataException {
        UserBean ub = new UserBean("polidori", "Studente", "Marco");
        Role role = Role.Studente;
        try {
            new StudenteView(ub).control();
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}