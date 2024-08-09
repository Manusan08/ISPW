package it.uniroma2.ispw.controller.controllergrafico2.studente;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;

public class StudenteView extends TemplateView {
    GestisciPrenotazioneView gestisciPrenotazione=new GestisciPrenotazioneView(this.usrBean);
    PrenotaPostoView prenotaPostoView =new PrenotaPostoView();
    VisualizzaAuleDisponibiliView visualizzaAuleDisponibiliView=new VisualizzaAuleDisponibiliView();

    public StudenteView(UserBean userBean) {
        super(userBean);
    }
    @Override
    public void control() throws SystemException, InvalidDataException, IOException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> gestisciPrenotazione.control();
                case 2 -> prenotaPostoView.control();
                case 3 -> visualizzaAuleDisponibiliView.control();
                case 4 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
                }
        }
    }

    @Override
    public List<String> getOptions() {
        return List.of("Gestisci Prenotazione", "prenota aula", "visualizza aule disponibili","esci");
    }

    @Override
    public String getHeader() {
        return "STUDENTE";
    }


}

