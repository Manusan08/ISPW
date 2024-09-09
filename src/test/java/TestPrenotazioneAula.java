import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.enums.Orario;
import it.uniroma2.ispw.utils.facade.DocenteFacade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.sql.Date;


import static org.junit.jupiter.api.Assertions.*;

class TestPrenotazioneAula {

    private final DocenteFacade docenteFacade = new DocenteFacade();
    private AulaBean aulaBean;
    private UserBean userBean;

    @BeforeEach
    void setUp() throws Exception {
        startTheAppForPersistenceLayer();

        // Initialize AulaBean and UserBean for the test
        aulaBean = new AulaBean();
        aulaBean.setIdAula("A1"); // Adjust ID as needed
        aulaBean.setPosti(150);
        aulaBean.setProiettore(true);
        aulaBean.setBanchiDisegno(false);
        aulaBean.setComputer(false);

        userBean = new UserBean();
        userBean.setEmail("utente5");
        userBean.setNome("Giacomini");
    }

    @Test
    void testPrenotazioneAula() throws Exception {
        // Prepare the data for the test
        Date dataLezione = Date.valueOf("2024-09-10");
        Orario orario = Orario.FASCIAUNO; // 12:00-14:00
        String descrizione = "Lezione di prova";
        String materia = "Matematica";
        boolean isRicorrente = false;

        PrenotazioneAulaBean prenotazione = new PrenotazioneAulaBean();
        prenotazione.setEmail(userBean.getEmail());
        prenotazione.setNomeDocente(userBean.getNome());
        prenotazione.setIdAula(aulaBean.getIdAula());
        prenotazione.setGiornoLezione(dataLezione);
        prenotazione.setRicorente(isRicorrente);
        prenotazione.setDescrizione(descrizione);
        prenotazione.setMateria(materia);
        prenotazione.setOraLezione(orario);

        // Execute the test
        boolean risultato = docenteFacade.prenota(prenotazione);

        // Assert that the result is true, indicating a successful reservation
        assertTrue(risultato, "La prenotazione dell'aula dovrebbe andare a buon fine.");

        // Further checks can be added here if necessary to validate the reservation
    }

    void startTheAppForPersistenceLayer() throws Exception {
        Main application = new Main();
        Method setPersistenceLayerAndUi = Main.class.getDeclaredMethod("setPersistenceLayerAndUi");
        setPersistenceLayerAndUi.setAccessible(true);
        setPersistenceLayerAndUi.invoke(application);
    }
}
