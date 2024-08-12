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

public class CercaAulePerIdView extends TemplateView {
    GestisciAuleController gestisciAuleController = new GestisciAuleController();

    public CercaAulePerIdView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("inserisci l'id dell'aula");

        String iDaula = scanner.nextLine();

         AulaBean aulaBean= gestisciAuleController.getAulaById(iDaula);
        System.out.println("Dettagli dell'Aula:");
        System.out.println("==================");
        System.out.printf("ID Aula         : %s%n", aulaBean.getIdAula());
        System.out.printf("Numero di Posti : %d%n", aulaBean.getPosti());
        System.out.printf("Proiettore      : %s%n", aulaBean.isProiettore() ? "Presente" : "Assente");
        System.out.printf("Banchi da Disegno: %s%n", aulaBean.isBanchiDisegno() ? "Presenti" : "Assenti");
        System.out.printf("Computer        : %s%n", aulaBean.isComputer() ? "Presente" : "Assente");
        System.out.println("==================");
        PrenotazioneAulaView prenotazioneView = new PrenotazioneAulaView(aulaBean,this.usrBean);
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
