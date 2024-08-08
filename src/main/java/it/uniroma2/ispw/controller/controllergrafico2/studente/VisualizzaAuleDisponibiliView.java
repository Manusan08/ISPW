package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciPrenotazioneController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.DateParser;
import it.uniroma2.ispw.utils.exception.InvalidDataException;

import java.io.IOException;
import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView {
    @Override
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
        printTable(new GestisciPrenotazioneController().getAuleByDay(ab));
    }

    private void visualizzaAuleDisponibiliPerOrario() throws IOException, InvalidDataException {
        AulaBean ab=new AulaBean();
        ab.setOrarioLezione(DateParser.parseStringToDate(getDesiredIn("inserisci orario","inserisci data nel formato hh:mm")));
        printTable(new GestisciPrenotazioneController().getAuleByDay(ab));
    }

    @Override
    protected List<String> getOptions() {
        return List.of("visualizza aule disponibili nella fascia oraria","visualizza le aule disponibili per un giorno specifico");
    }

    @Override
    protected String getHeader() {
        return "Visualizza le Aule Disponibili";
    }

    @Override
    public void update(String... msg) {

    }
}
