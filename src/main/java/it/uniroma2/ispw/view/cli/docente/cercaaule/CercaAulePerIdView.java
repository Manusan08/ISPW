package it.uniroma2.ispw.view.cli.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.bean.UserBean;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;
import it.uniroma2.ispw.utils.facade.DocenteFacade;
import it.uniroma2.ispw.view.cli.TemplateView;
import it.uniroma2.ispw.view.cli.docente.prenotaaula.PrenotazioneAulaView;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CercaAulePerIdView extends TemplateView {
    private DocenteFacade docenteFacade = new DocenteFacade();

    public CercaAulePerIdView(UserBean usrBean) {
        super(usrBean);
    }

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("inserisci l'id dell'aula");

        String iDaula = scanner.nextLine();

         AulaBean aulaBean= docenteFacade.getAulaById(iDaula);
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
        return Collections.emptyList();
    }

    @Override
    protected String getHeader() {
        return null;
    }
}
