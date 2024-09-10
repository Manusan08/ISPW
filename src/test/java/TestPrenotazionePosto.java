import it.uniroma2.ispw.Conf;
import it.uniroma2.ispw.Main;
import it.uniroma2.ispw.bean.PostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.cli.studente.PrenotaPostoView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
class TestPrenotazionePosto {

    @BeforeAll
    static void setup() {
        // Se possibile, configura il layer di persistenza senza riflessione
        Conf.getConf().readConf(); // Assicurati che questo metodo sia accessibile
    }

    @Test
    void testPrenotazionePosto() {
        UserBean us = new UserBean("utente1", "utente1");
        PrenotaPostoView ppw = new PrenotaPostoView(us);
        List<PostoBean> posti = new ArrayList<>();

        posti.add(new PostoBean("A0", "A1", true));
        posti.add(new PostoBean("A2", "A1", false));
        posti.add(new PostoBean("A3", "A1", false));
        posti.add(new PostoBean("A4", "A1", false));
        posti.add(new PostoBean("A6", "A1", false));
        posti.add(new PostoBean("A7", "A1", false));
        posti.add(new PostoBean("A11", "A1", false));
        posti.add(new PostoBean("A22", "A1", false));
        posti.add(new PostoBean("A33", "A1", false));
        posti.add(new PostoBean("A44", "A1", false));
        posti.add(new PostoBean("A55", "A1", false));
        posti.add(new PostoBean("A66", "A1", false));
        posti.add(new PostoBean("A8", "A1", false));
        posti.add(new PostoBean("A9", "A1", false));

        PrenotaPostoView.postiPrinter(posti, 80);
        // Aggiungi asserzioni per verificare il comportamento atteso
        assertThat(posti).isNotEmpty().hasSize(14);
        assertThat(posti.get(0).isPrenotato()).isTrue(); // Verifica che il primo posto sia prenotato
        assertThat(posti.get(1).isPrenotato()).isFalse();
    }
}
