import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico2.studente.PrenotaPostoView;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestPrenotazionePosto {
    @Test
    void prenotazioneTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        startTheAppForPersistenceLayer();
        UserBean us=new UserBean("utente1","utente1");
        PrenotaPostoView ppw =new PrenotaPostoView(us);
        List< PostoBean> posti=new ArrayList<>();

        posti.add(new PostoBean("A0","A1",true));
        posti.add(new PostoBean("A2","A1",false));
        posti.add(new PostoBean("A3","A1",false));
        posti.add(new PostoBean("A4","A1",false));
        posti.add(new PostoBean("A6","A1",false));
        posti.add(new PostoBean("A7","A1",false));
        posti.add(new PostoBean("A11","A1",false));
        posti.add(new PostoBean("A22","A1",false));
        posti.add(new PostoBean("A33","A1",false));
        posti.add(new PostoBean("A44","A1",false));
        posti.add(new PostoBean("A55","A1",false));
        posti.add(new PostoBean("A66","A1",false));
        posti.add(new PostoBean("A8","A1",false));
        posti.add(new PostoBean("A9","A1",false));


        ppw.postiPrinter(posti,80);

    }
    void startTheAppForPersistenceLayer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Main application = new Main();
        Method prvPersistenceLayer = Main.class.getDeclaredMethod("setPersistenceLayerAndUi");
        prvPersistenceLayer.setAccessible(true);
        prvPersistenceLayer.invoke(application);
    }



}
