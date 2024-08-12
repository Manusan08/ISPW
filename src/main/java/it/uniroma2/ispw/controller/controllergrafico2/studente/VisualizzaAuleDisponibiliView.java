package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView {
    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {

    }

    /*é   @Override
        public void control() throws InvalidDataException, IOException {
            int choice=userChoice();
            boolean cond=true;
            while(cond)
            {
                switch(choice){
                    case 1->visualizzaAuleDisponibiliPerOrario();
                    case 2->visuallizaAueDisponibiliNelGiorno();
                    default->System.out.println("Riprova");
                }
            }

        }
        private void visuallizaAueDisponibiliNelGiorno() throws IOException, InvalidDataException {
            AulaBean ab=new AulaBean();
            ab.setOrarioLezione(DateParser.parseStringToDate(getDesiredIn("inserisci data","inserisci data nel formato gg/mm/yyyy")));
            printTable(new GestisciPrenotazioneAulaController().getAuleByDay(ab));
        }

        private void visualizzaAuleDisponibiliPerOrario() throws IOException, InvalidDataException {
            AulaBean ab=new AulaBean();
            ab.setOrarioLezione(DateParser.parseStringToDate(getDesiredIn("inserisci orario","inserisci data nel formato hh:mm")));
            printTable(new GestisciPrenotazioneAulaController().getAuleByDay(ab));
        }
    */
    @Override
    protected List<String> getOptions() {
        return List.of("visualizza aule disponibili nella fascia oraria","visualizza le aule disponibili per un giorno specifico");
    }

    @Override
    protected String getHeader() {
        return "Visualizza le Aule Disponibili";
    }


}
