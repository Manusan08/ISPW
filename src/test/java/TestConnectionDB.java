
import it.uniroma2.ispw.utils.ConnectionDB;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;



/*
    Il test controlla la connessione con il DataBase.
    Ha successo se la connessione è correttamente stabilita, fallisce altrimenti

    Manuel
*/

class TestConnectionDB {
    @Test
    void testgetConnection() throws SystemException {

        int value = 0;

        if (ConnectionDB.getConnection() != null) {
            value = 1;
        }

        Assertions.assertEquals(1, value);

    }
}
