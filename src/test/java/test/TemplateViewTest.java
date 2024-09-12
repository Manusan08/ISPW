package test;

import it.uniroma2.ispw.view.cli.TemplateView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateViewTest extends TemplateView {

    @Override
    public void control() {
        // Implementa il comportamento richiesto, se necessario.
    }

    @Override
    protected List<String> getOptions() {
        return List.of("1 ", "2 ", "3 ");
    }

    @Override
    protected String getHeader() {
        return "ciao";
    }

    @Test
    void testUserChoice() {
        TemplateViewTest test = new TemplateViewTest();
        int choice = test.userChoice();

        // Verifica che la scelta dell'utente sia quella attesa (modifica l'asserzione in base a ciò che ti aspetti).
        assertEquals(1, choice, "L'opzione scelta dall'utente dovrebbe essere 1");
    }
}
