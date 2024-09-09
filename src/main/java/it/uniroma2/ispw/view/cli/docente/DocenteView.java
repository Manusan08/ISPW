package it.uniroma2.ispw.view.cli.docente;

import it.uniroma2.ispw.view.cli.TemplateView;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.cli.docente.cercaaule.CercaAulaView;

import it.uniroma2.ispw.view.cli.docente.prenotaaula.GestisciPrenotazioniView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class DocenteView extends TemplateView {

    GestisciPrenotazioniView gestisciPrenotazioniView = new GestisciPrenotazioniView(this.usrBean);

    CercaAulaView cercaAulaView=  new CercaAulaView(this.usrBean);

    public DocenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {

                case 1 -> gestisciPrenotazioniView.control();
                case 2 -> cercaAulaView.control();
                case 3 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of( "Gestisci prenotazioni", "cerca aule per la prenotazione", "Esci");
    }

    @Override
    protected String getHeader() {
        return "Docente";
    }


}

