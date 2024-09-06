import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.facade.ManIntheMiddleFaçade;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;
import it.uniroma2.ispw.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    Il test utilizza il ManIntheMiddleFaçade per trovare un'Aula tramite il numero di posti e altri filtri;
    Fallisce se l'aula trovata non ha come caratteristiche:
    Numero posti => 140
    proiettore = true
    banchi disegno = false
    computer = false
*/
class TestAula {

    @Test
    void testAulaPerFiltri() throws SystemException, ItemNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // Crea un'istanza della facade
        startTheAppForPersistenceLayer();
        ManIntheMiddleFaçade facade = new ManIntheMiddleFaçade();

        // Crea un oggetto AulaBean con i filtri richiesti
        AulaBean filtroAula = new AulaBean();
        filtroAula.setPosti(150);
        filtroAula.setProiettore(true);
        filtroAula.setBanchiDisegno(false);
        filtroAula.setComputer(false);

        // Chiama il metodo per cercare le aule con i filtri specificati
        List<AulaBean> auleTrovate = facade.chekAula(filtroAula);

        // Verifica che ci sia almeno un'aula trovata
        assertFalse(auleTrovate.isEmpty(), "Nessuna aula trovata con i filtri specificati.");

        // Prendi la prima aula trovata
        AulaBean aulaTrovata = auleTrovate.get(0);

        // Verifica le caratteristiche dell'aula trovata
        assertEquals(150, aulaTrovata.getPosti(), "Il numero di posti dell'aula non corrisponde.");
        assertTrue(aulaTrovata.isProiettore(), "L'aula non ha un proiettore come richiesto.");
        assertFalse(aulaTrovata.isBanchiDisegno(), "L'aula non dovrebbe avere banchi da disegno.");
        assertFalse(aulaTrovata.isComputer(), "L'aula non dovrebbe avere computer.");
    }
    void startTheAppForPersistenceLayer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Main application = new Main();
        Method prvPersistenceLayer = Main.class.getDeclaredMethod("setPersistenceLayerAndUi");
        prvPersistenceLayer.setAccessible(true);
        prvPersistenceLayer.invoke(application);
    }
}
