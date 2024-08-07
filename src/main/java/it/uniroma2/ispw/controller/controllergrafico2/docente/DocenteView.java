package it.uniroma2.ispw.controller.controllergrafico2.docente;

import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.bean.LoginBean;
import it.uniroma2.ispw.bean.UserBean;
import java.util.List;

public class DocenteView  extends TemplateView {
    CreaPrenotazioneAulaView creaPrenotazioneAulaView=new CreaPrenotazioneAulaView();

    MostraFiltriView mostraFiltriView = new MostraFiltriView();

    PrendiDatiPrenotazioneView prendiDatiPrenotazioneView = new PrendiDatiPrenotazioneView();
    MostraClassiDisponibiliView mostraClassiDisponibiliView = new MostraClassiDisponibiliView();
    public DocenteView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public LoginBean control() {
        int choice;
        while (true) {
            choice = this.userChoice();
            switch (choice) {
                case 1 -> creaPrenotazioneAulaView.control();
                case 2 -> mostraClassiDisponibiliView.mostraClassiDisponibili();
                case 3 -> mostraFiltriView.mostraFiltri();
                case 4 -> System.exit(0);
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

