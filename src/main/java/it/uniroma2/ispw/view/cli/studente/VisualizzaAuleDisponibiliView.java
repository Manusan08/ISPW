package it.uniroma2.ispw.view.cli.studente;


import it.uniroma2.ispw.utils.facade.StudenteFacade;
import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.view.cli.TemplateView;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

public class VisualizzaAuleDisponibiliView extends TemplateView {


    StudenteFacade intheMiddle = new StudenteFacade();

    @Override
    public void control() throws SystemException, IOException, LoginException, ItemNotFoundException {
        visualizzaAuleDisponibili();

    }

    private void visualizzaAuleDisponibili() {
        List<PrenotazioneAulaBean> auleDisponibili=intheMiddle.getAvailableClass();
        auleAvailablePrinter(auleDisponibili);
    }

    @Override
    protected List<String> getOptions() {
        return List.of();
    }

    @Override
    protected String getHeader() {
        return "";
    }



    public static void auleAvailablePrinter(List<PrenotazioneAulaBean> pab){

        System.out.printf("%-20s %-12s",ANSI_PURPLE+ "ID Aula:","Fascia Oraria:\n");
        System.out.println("-".repeat(50)+ANSI_RESET);
        for(PrenotazioneAulaBean pb : pab){
            System.out.printf("%-16s %-6s",pb.getIdAula(),pb.getOraLezione().getFasciaOraria());
            System.out.println();
        }
    }
}

