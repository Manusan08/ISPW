import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.facade.ManIntheMiddleFaçade;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PrenotazionePostoTest {

    @Test
    public void testVisualizzaClassiDisponibili() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        {
            startTheAppForPersistenceLayer();

            ManIntheMiddleFaçade facede = new ManIntheMiddleFaçade();
            List <PrenotazioneAulaBean> prenotazioni =facede.getAvailableClass();
            for(PrenotazioneAulaBean i:prenotazioni )
                System.out.println(i.getIdAula()+ " "+ i.getOraLezione1());
        }
    }

    void startTheAppForPersistenceLayer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Main application = new Main();
        Method prvPersistenceLayer = Main.class.getDeclaredMethod("setPersistenceLayerAndUi");
        prvPersistenceLayer.setAccessible(true);
        prvPersistenceLayer.invoke(application);
    }
    }


