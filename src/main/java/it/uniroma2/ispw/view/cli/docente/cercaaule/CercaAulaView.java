package it.uniroma2.ispw.view.cli.docente.cercaaule;

import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.view.cli.TemplateView;

import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class CercaAulaView extends TemplateView {
    CercaAulaPerFiltriView cercaAulaPerFiltriView = new CercaAulaPerFiltriView(this.usrBean);

    CercaTutteLeAuleView cercaTutteLeAuleView = new CercaTutteLeAuleView(this.usrBean);

    CercaAulePerIdView cercaAulePerIdView = new CercaAulePerIdView(this.usrBean);

    public CercaAulaView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException {
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
