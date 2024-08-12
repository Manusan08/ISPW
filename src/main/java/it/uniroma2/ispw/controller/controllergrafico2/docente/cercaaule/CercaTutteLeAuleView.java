package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula.PrenotazioneAulaView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CercaTutteLeAuleView extends TemplateView {
    GestisciAuleController gestisciAuleController = new GestisciAuleController();

    public CercaTutteLeAuleView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ecco tutte le Aule dell'edificio");
        List<AulaBean> aulabeanlist= gestisciAuleController.getAllAule();
        printTable(aulabeanlist);
        System.out.println("Aule disponibili:");
        for (int i = 0; i < aulabeanlist.size(); i++) {
            AulaBean ab = aulabeanlist.get(i);
            System.out.println((i + 1) + ". Aula ID: " + ab.getIdAula() + ", Posti: " + ab.getPosti() + ", Proiettore: " + ab.isProiettore() + ", Computer: " + ab.isComputer() + ", Banchi da disegno: " + ab.isBanchiDisegno());
        }

        // Selezione dell'aula da parte dell'utente
        System.out.print("Seleziona il numero dell'aula desiderata: ");
        int scelta = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        if (scelta < 1 || scelta > aulabeanlist.size()) {
            System.out.println("Scelta non valida.");
            return;
        }
        AulaBean aulaSelezionata = aulabeanlist.get(scelta - 1);
        PrenotazioneAulaView prenotazioneView = new PrenotazioneAulaView(aulaSelezionata,this.usrBean);
        prenotazioneView.start();
    }

    @Override
    protected List<String> getOptions() {
        return null;
    }

    @Override
    protected String getHeader() {
        return null;
    }
}
