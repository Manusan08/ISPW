package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule.CercaAulaPerFiltriView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule.CercaAulePerIdView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule.CercaTutteLeAuleView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class CercaAulaView extends TemplateView {
    CercaAulaPerFiltriView cercaAulaPerFiltriView = new CercaAulaPerFiltriView(this.usrBean);

    CercaTutteLeAuleView cercaTutteLeAuleView = new CercaTutteLeAuleView();

    CercaAulePerIdView cercaAulePerIdView = new CercaAulePerIdView();
    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> cercaAulaPerFiltriView.control();
                case 2 -> cercaTutteLeAuleView.control();
                case 3 -> cercaAulePerIdView.control();
                case 4 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Cerca Aula per Filtri", "Mostrami tutte le Aule", "Cerca questa Aula con Id", "Esci");

    }

    @Override
    protected String getHeader() {
        return "CERCA AULA";
    }
}
