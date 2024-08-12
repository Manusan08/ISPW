package it.uniroma2.ispw.controller.controllergrafico2.docente.cercaaule;

import it.uniroma2.ispw.bean.AulaBean;
import it.uniroma2.ispw.controller.controllerApplicativo.GestisciAuleController;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CercaAulePerIdView extends TemplateView {
    GestisciAuleController gestisciAuleController = new GestisciAuleController();
    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException, ItemNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("inserisci l'id dell'aula");

        String iDaula = scanner.nextLine();

         AulaBean aulabean= gestisciAuleController.getAulaById(iDaula);
        //bisogna trovare il modo per printare tutte le componeneti di aulabean

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
