package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.PrenotazionePostoBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneAulaController;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazionePostoController;
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
        PrenotazioneAulaBean pb = new PrenotazioneAulaBean();
        mostraPrenotazioni(this.usrBean);
        String str = getDesiredIn("id aula", "inserisci id aula per cui vuoi effettuare modifica");
        pb.setIdAula(str);

        if (modificaDescrizione(pb)) {
            System.out.println("Modifica avvenuta con successo");
        } else {
            System.out.println("Errore nellla modifica");
        }

    }

    private boolean modificaDescrizione(PrenotazioneAulaBean pb) throws IOException {
        String message = getDesiredIn("Modifica descrizione", "inserisci il messaggio ");
        pb.setDescrizione(message);
        return new GestisciPrenotazioneAulaController().modificaDescrizionePrenotazione(pb);
    }

    private void eliminaPrenotazione() {
        PrenotazionePostoBean ppb = new PrenotazionePostoBean();
        try {
            ppb.setIdPrenotazionePosto(getDesiredIn("id", "inserisci l'id della prenotazione"));
            new GestisciPrenotazioneAulaController().removePrenotazione();
        } catch (IOException e) {
            System.out.println("Impossibile leggere i dati ");
        }
    }

    private void mostraPrenotazioni(UserBean usrBean) {
        UserBean us = new UserBean(this.usrBean.getEmail());
        printTable(new GestisciPrenotazionePostoController().getPrenotazioneByName(us));

    }

    @Override
    protected List<String> getOptions() {
        return List.of("mostra Prenotazioni", "elimina prenotazione", "aggiorna descrizione");
    }

    @Override
    protected String getHeader() {
        return "Gestione Prenotazioni";
    }



}
