package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;

import java.io.IOException;
import java.util.List;

public class GestisciPrenotazioniView extends TemplateView {
    public GestisciPrenotazioniView(UserBean usrBean) {
    }

    public void control() throws IOException {
        int choice;
        boolean cond = true;

        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> mostraPrenotazioni(this.usrBean);
                case 2 -> eliminaPrenotazione();
                case 3 -> modificaPrenotazione();
                case 4 -> cond = false;
                default -> System.out.println("riprova");
            }
        }
    }

    private void modificaPrenotazione() throws IOException {
        AulaBean ab = new AulaBean();
        PrenotazioneBean pb = new PrenotazioneBean();
        mostraPrenotazioni(this.usrBean);
        String str = getDesiredIn("id aula", "inserisci id aula per cui vuoi effettuare modifica");
        ab.setIdAula(str);
        pb.setAula(ab);

        if (modificaDescrizione(pb)) {
            System.out.println("Modifica avvenuta con successo");
        } else {
            System.out.println("Errore nellla modifica");
        }

    }

    private boolean modificaDescrizione(PrenotazioneBean pb) throws IOException {
        String message = getDesiredIn("Modifica descrizione", "inserisci il messaggio ");
        pb.setDescrizione(message);
        return new GestisciPrenotazioneController().modificaDescrizionePrenotazione(pb);
    }

    private void eliminaPrenotazione() {
        PrenotazioneBean pb = new PrenotazioneBean();
        try {
            pb.setIdPrenotazione(getDesiredIn("id", "inserisci l'id della prenotazione"));
            new GestisciPrenotazioneController().removePrenotazione();
        } catch (IOException e) {
            System.out.println("Impossibile leggere i dati ");
        }
    }

    private void mostraPrenotazioni(UserBean usrBean) {
        UserBean us = new UserBean(this.usrBean.getEmail(), this.usrBean.getNome());
        printTable(new GestisciPrenotazioneController().getPrenotazioneByName(us));
    }

    @Override
    protected List<String> getOptions() {
        return List.of("mostra Prenotazioni", "elimina prenotazione", "aggiorna descrizione");
    }

    @Override
    protected String getHeader() {
        return "Gestione Prenotazioni";
    }

    @Override
    public void update(String... msg) {

    }

}
