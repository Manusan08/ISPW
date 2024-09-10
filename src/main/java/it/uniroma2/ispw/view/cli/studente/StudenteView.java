package it.uniroma2.ispw.view.cli.studente;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.CampiVuotiExeption;
import it.uniroma2.ispw.view.cli.TemplateView;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudenteView extends TemplateView {
    GestisciPrenotazionePostoView gestisciPrenotazioniPosto =new GestisciPrenotazionePostoView(this.usrBean);
    VisualizzaAuleDisponibiliView visualizzaAuleDisponibiliView=new VisualizzaAuleDisponibiliView();
    PrenotaPostoView prenotaPosto=new PrenotaPostoView(this.usrBean);
    public StudenteView(UserBean userBean) {
        super(userBean);
    }
    @Override
    public void control() throws SystemException, IOException, ItemNotFoundException, LoginException, SQLException, CampiVuotiExeption {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> gestisciPrenotazioniPosto.control();
                case 2 -> prenotaPosto.control();
                case 3 -> visualizzaAuleDisponibiliView.control();
                case 4 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    public List<String> getOptions() {
        return List.of("Gestisci Prenotazione", "Prenota posto", "visualizza aule disponibili","esci");
    }

    @Override
    public String getHeader() {
        return "STUDENTE";
    }

}

