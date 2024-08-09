package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;

import java.io.IOException;
import java.util.List;

public class GestisciPrenotazioneView extends TemplateView {

    @Override
    public void control() throws IOException {
        int choice;
        boolean cond= true;

        while (cond){
            choice =this.userChoice();
            switch (choice){
                case 1->eliminaPrenotazione();
                case 2->visualizzaPrenotazioni();
                case 3 -> cond=false;
                default -> System.out.println("riprova");
            }
        }
        System.exit(0);
    }

    private void eliminaPrenotazione() {
        AulaBean ab=new AulaBean();
            try{
                ab.setIdAula(getDesiredIn("id","inserisci l'id della prenotazione"));
                new GestisciPrenotazioneController().removePrenotazione();
            }catch (IOException e){
                System.out.println("Impossibile leggere i dati ");
            }
    }

    void visualizzaPrenotazioni(){
        new GestisciPrenotazioneController().getAllReservation();
    }

    @Override
    protected List<String> getOptions() {
        return List.of("elimina prenotazione","visualizza prenotazione");
    }

    @Override
    protected String getHeader() {
        return "GESTIONE PRENOTAZIONI";
    }

    @Override
    public void update(String... msg) {

    }
}
