package it.uniroma2.ispw.view.cli.docente.prenotaaula;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.GestisciCreazionePrenotazioneAulaController;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.view.cli.TemplateView;

import java.io.IOException;
import java.util.List;

public class GestisciPrenotazioniView extends TemplateView {
    public GestisciPrenotazioniView(UserBean usrBean) {
        super(usrBean);
    }
    public void control() throws IOException, CampiVuotiExeption {
        int choice;
        boolean cond = true;

        while (cond) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> mostraPrenotazioni();
                case 2 -> eliminaPrenotazione();
                case 3 -> modificaPrenotazione();
                case 4 -> cond = false;
                default -> System.out.println("riprova");
            }
        }
    }

    private void modificaPrenotazione() throws IOException, CampiVuotiExeption {
        AulaBean ab = new AulaBean();
        PrenotazioneAulaBean pb = new PrenotazioneAulaBean();
        mostraPrenotazioni();
        String str = getDesiredIn("id aula", "inserisci id aula per cui vuoi effettuare modifica");
        ab.setIdAula(str);
        pb.setIdAula(str);

        if (modificaDescrizione(pb)) {
            System.out.println("Modifica avvenuta con successo");
        } else {
            System.out.println("Errore nellla modifica");
        }

    }

    private boolean modificaDescrizione(PrenotazioneAulaBean pb) throws CampiVuotiExeption, IOException {
        String message = getDesiredIn("Modifica descrizione", "inserisci il messaggio ");
        pb.setDescrizione(message);
        return new GestisciCreazionePrenotazioneAulaController().modificaDescrizionePrenotazione();
    }

    private void eliminaPrenotazione() {
        PrenotazioneAulaBean pb = new PrenotazioneAulaBean();
        try {
            pb.setIdPrenotazioneAula(getDesiredIn("id", "inserisci l'id della prenotazione"));
            new GestisciCreazionePrenotazioneAulaController().removePrenotazione();
        } catch (CampiVuotiExeption | IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void mostraPrenotazioni() {
        //da implementare
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
