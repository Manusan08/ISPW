package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.util.List;

public class DocenteView  extends TemplateView {


    MostraFiltriView mostraFiltriView = new MostraFiltriView();

    PrendiDatiPrenotazioneView prendiDatiPrenotazioneView = new PrendiDatiPrenotazioneView();
    MostraClassiDisponibiliView mostraClassiDisponibiliView = new MostraClassiDisponibiliView();
    public DocenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {

                case 1 -> mostraClassiDisponibiliView.mostraClassiDisponibili();
                case 2 -> mostraFiltriView.mostraFiltri();
                case 3 -> System.exit(0);
                default -> System.out.println("Opzione non valida");
            }
        }
    }

    @Override
    protected List<String> getOptions() {
        return List.of("Crea Prenotazione","mostra classi","mostra filtri" , "esci");
    }

    @Override
    protected String getHeader() {
        return "Docente";
    }

    @Override
    public void update(String... msg) {
        if (msg.length != 0)
            System.out.println("\033[33m\n*** Nuova notifica: [" + msg[0] + "] ***\033[0m");
    }
}

