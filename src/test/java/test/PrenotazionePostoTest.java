package test;

import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;

import it.uniroma2.ispw.utils.facade.StudenteFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrenotazionePostoTest {

    @BeforeAll
    static void setup() {
        try {
            startTheAppForPersistenceLayer();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start the app for persistence layer setup.", e);
        }
    }

    @Test
    void testVisualizzaClassiDisponibili() {
        StudenteFacade facade = new StudenteFacade();
        List<PrenotazioneAulaBean> prenotazioni = facade.getAvailableClass();

        // Asserzione per verificare che la lista di prenotazioni non sia vuota
        assertThat(prenotazioni).isNotNull().isNotEmpty();

        // Asserzione per verificare che le prenotazioni abbiano dati validi
        for (PrenotazioneAulaBean prenotazione : prenotazioni) {
            assertThat(prenotazione.getIdAula()).isNotNull().isNotEmpty();
            assertThat(prenotazione.getOraLezione1()).isNotNull();
            // Altre asserzioni possono essere aggiunte a seconda dei dettagli richiesti
        }
    }

    static void startTheAppForPersistenceLayer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Main application = new Main();
        Method prvPersistenceLayer = Main.class.getDeclaredMethod("setPersistenceLayerAndUi");
        prvPersistenceLayer.setAccessible(true);
        prvPersistenceLayer.invoke(application);
    }
}
