package it.uniroma2.ispw.controller.controllergrafico2.docente.prenotaaula;

import it.uniroma2.ispw.bean.PrenotazioneAulaBean;
import it.uniroma2.ispw.controller.controllergrafico2.TemplateView;
import it.uniroma2.ispw.utils.exception.InvalidDataException;
import it.uniroma2.ispw.utils.exception.SystemException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ConfermPrenotazioneView extends TemplateView {


    public static boolean confermaPrenotazione(PrenotazioneAulaBean prenotazione) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("Conferma i dettagli della prenotazione:");
        System.out.println("Materia: " + prenotazione.getMateria());
        System.out.println("Professore: " + prenotazione.getNomeDocente());
        System.out.println("Orario: " + prenotazione.getOraLezione().getFasciaOraria());
        System.out.print("Confermi? (s/n): ");
        boolean choice = true;
        do {
            Scanner scanner = new Scanner(System.in);
            String conferma = scanner.nextLine();
            if (conferma.equals("s".toLowerCase()))
                return true;

        } while (choice);
        return false;
    }



    @Override
    public void control() throws SystemException, InvalidDataException, IOException, LoginException {

    }

    @Override
    protected List<String> getOptions() {
        return List.of("Conferma Prenotazione");
    }

    @Override
    protected String getHeader() {
        return "Prenotazione Aula";
    }


}
