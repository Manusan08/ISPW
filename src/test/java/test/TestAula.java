package test;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.utils.facade.DocenteFacade;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import org.junit.jupiter.api.Test;
import it.uniroma2.ispw.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestAula {

    @Test
    void testAulaPerFiltri() throws ItemNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, SystemException {
        // Crea un'istanza della facade
        startTheAppForPersistenceLayer();
        DocenteFacade docenteFacade = new DocenteFacade();

        // Crea un oggetto AulaBean con i filtri richiesti
        AulaBean filtroAula = new AulaBean();
        filtroAula.setPosti(80);
        filtroAula.setProiettore(true);
        filtroAula.setBanchiDisegno(false);
        filtroAula.setComputer(false);

        // Chiama il metodo per cercare le aule con i filtri specificati
        List<AulaBean> auleTrovate = docenteFacade.chekAula(filtroAula);

        // Verifica che ci sia almeno un'aula trovata
        assertFalse(auleTrovate.isEmpty(), "Nessuna aula trovata con i filtri specificati.");

        // Prendi la prima aula trovata
        AulaBean aulaTrovata = auleTrovate.get(0);

        // Verifica le caratteristiche dell'aula trovata
        assertEquals(80, aulaTrovata.getPosti(), "Il numero di posti dell'aula non corrisponde.");
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
