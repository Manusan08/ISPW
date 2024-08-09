package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.util.List;

public class DocenteView extends TemplateView {
    PrenotaAulaView prenotaAulaView = new PrenotaAulaView();
    MostraFiltriView mostraAulePrenotateView = new MostraFiltriView(this.usrBean);
    GestisciPrenotazioniView gestisciPrenotazioniView = new GestisciPrenotazioniView(this.usrBean);

    public DocenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> prenotaAulaView.control();
                case 2 -> gestisciPrenotazioniView.control();
                case 3 -> mostraAulePrenotateView.control();
                case 4 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Prenota aula", "Gestisci prenotazioni", "Mostra aule prenotate", "Esci");
    }

    @Override
    protected String getHeader() {
        return "Docente";
    }


}

