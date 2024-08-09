package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;

import java.io.IOException;
import java.util.List;

public class GestisciPrenotazioniView extends TemplateView {
    public void control() throws IOException {
            int choice;
            boolean cond = true;

            while (cond) {
                choice = this.userChoice();
                switch (choice) {
                    case 1 -> mostraPrenotazioni();
                    case 2 -> eliminaPrenotazione();
                    case 3 -> aggiornaDescrizione();
                    case 4 -> cond = false;
                    default -> System.out.println("riprova");
                }

            }
    }

    private void aggiornaDescrizione() {
        // TODO document why this method is empty
    }

    private void eliminaPrenotazione() {
        // TODO document why this method is empty
    }

    private void mostraPrenotazioni() {
        // TODO document why this method is empty
    }

    @Override
    protected List<String> getOptions() {
        return List.of("mostra Prenotazioni","elimina prenotazione","aggiorna descrizione");
    }

    @Override
    protected String getHeader() {
        return "Gestione Prenotazioni";
    }

    @Override
    public void update(String... msg) {

    }

}
