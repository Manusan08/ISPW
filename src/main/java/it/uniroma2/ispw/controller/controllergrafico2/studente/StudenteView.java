package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;

import java.util.List;

public class StudenteView extends TemplateView {
    CercaAulaView cercaAulaView=new CercaAulaView();
    EliminaPrenotazioneView eliminaPrenotazioneView=new EliminaPrenotazioneView();
    VisualizzaAuleDisponibiliView visualizzaAuleDisponibiliView=new VisualizzaAuleDisponibiliView();
    VisualizzaPrenotazioniAttiveView visualizzaPrenotazioniAttiveView= new VisualizzaPrenotazioniAttiveView();

    public StudenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public LoginBean control() {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> cercaAulaView.control();
                case 2 -> visualizzaAuleDisponibiliView.control();
                case 3 -> visualizzaPrenotazioniAttiveView.control();
                case 4 -> eliminaPrenotazioneView.control();
                case 5 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Cerca aule", "visualizza aule disponibili", "visualizza prenotazioni", "cancella prenotazioni", "esci");
    }

    @Override
    protected String getHeader() {
        return "Studente";
    }

    @Override
    public void update(String... msg) {
        if (msg.length != 0)
            System.out.println("\033[33m\n*** Nuova notifica: [" + msg[0] + "] ***\033[0m");
    }
}

