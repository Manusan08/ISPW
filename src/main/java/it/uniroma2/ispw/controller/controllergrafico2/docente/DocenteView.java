package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule.CercaAulaView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class DocenteView extends TemplateView {
    PrenotaAulaView prenotaAulaView = new PrenotaAulaView();
    MostraAulePrenotateView mostraAulePrenotateView = new MostraAulePrenotateView(this.usrBean);
    GestisciPrenotazioniView gestisciPrenotazioniView = new GestisciPrenotazioniView(this.usrBean);

    CercaAulaView cercaAulaView=  new CercaAulaView();

    public DocenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> prenotaAulaView.control();
                case 2 -> gestisciPrenotazioniView.control();
                case 3 -> cercaAulaView.control();
                case 4 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Prenota aula", "Gestisci prenotazioni", "Mostra aule con queste caratteristiche", "Esci");
    }

    @Override
    protected String getHeader() {
        return "Docente";
    }


}

